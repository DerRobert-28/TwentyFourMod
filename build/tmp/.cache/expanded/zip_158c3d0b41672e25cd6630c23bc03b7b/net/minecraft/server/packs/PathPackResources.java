package net.minecraft.server.packs;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult.PartialResult;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.FileUtil;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.resources.IoSupplier;
import org.slf4j.Logger;

public class PathPackResources extends AbstractPackResources {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Joiner PATH_JOINER = Joiner.on("/");
    private final Path root;

    public PathPackResources(String p_255754_, Path p_256025_, boolean p_256260_) {
        super(p_255754_, p_256260_);
        this.root = p_256025_;
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getRootResource(String... p_249041_) {
        FileUtil.validatePath(p_249041_);
        Path path = FileUtil.resolvePath(this.root, List.of(p_249041_));
        return Files.exists(path) ? IoSupplier.create(path) : null;
    }

    public static boolean validatePath(Path p_249579_) {
        return true;
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getResource(PackType p_249352_, ResourceLocation p_251715_) {
        Path path = this.root.resolve(p_249352_.getDirectory()).resolve(p_251715_.getNamespace());
        return getResource(p_251715_, path);
    }

    public static IoSupplier<InputStream> getResource(ResourceLocation p_250145_, Path p_251046_) {
        return FileUtil.decomposePath(p_250145_.getPath()).get().map(p_251647_ -> {
            Path path = FileUtil.resolvePath(p_251046_, p_251647_);
            return returnFileIfExists(path);
        }, p_248714_ -> {
            LOGGER.error("Invalid path {}: {}", p_250145_, p_248714_.message());
            return null;
        });
    }

    @Nullable
    private static IoSupplier<InputStream> returnFileIfExists(Path p_250506_) {
        return Files.exists(p_250506_) && validatePath(p_250506_) ? IoSupplier.create(p_250506_) : null;
    }

    @Override
    public void listResources(PackType p_251452_, String p_249854_, String p_248650_, PackResources.ResourceOutput p_248572_) {
        FileUtil.decomposePath(p_248650_).get().ifLeft(p_250225_ -> {
            Path path = this.root.resolve(p_251452_.getDirectory()).resolve(p_249854_);
            listPath(p_249854_, path, p_250225_, p_248572_);
        }).ifRight(p_252338_ -> LOGGER.error("Invalid path {}: {}", p_248650_, p_252338_.message()));
    }

    public static void listPath(String p_249455_, Path p_249514_, List<String> p_251918_, PackResources.ResourceOutput p_249964_) {
        Path path = FileUtil.resolvePath(p_249514_, p_251918_);

        try (Stream<Path> stream = Files.find(path, Integer.MAX_VALUE, (p_250060_, p_250796_) -> p_250796_.isRegularFile())) {
            stream.forEach(p_249092_ -> {
                String s = PATH_JOINER.join(p_249514_.relativize(p_249092_));
                ResourceLocation resourcelocation = ResourceLocation.tryBuild(p_249455_, s);
                if (resourcelocation == null) {
                    Util.logAndPauseIfInIde(String.format(Locale.ROOT, "Invalid path in pack: %s:%s, ignoring", p_249455_, s));
                } else {
                    p_249964_.accept(resourcelocation, IoSupplier.create(p_249092_));
                }
            });
        } catch (NotDirectoryException | NoSuchFileException nosuchfileexception) {
        } catch (IOException ioexception) {
            LOGGER.error("Failed to list path {}", path, ioexception);
        }
    }

    @Override
    public Set<String> getNamespaces(PackType p_251896_) {
        Set<String> set = Sets.newHashSet();
        Path path = this.root.resolve(p_251896_.getDirectory());

        try (DirectoryStream<Path> directorystream = Files.newDirectoryStream(path)) {
            for(Path path1 : directorystream) {
                String s = path1.getFileName().toString();
                if (ResourceLocation.isValidNamespace(s)) {
                    set.add(s);
                } else {
                    LOGGER.warn("Non [a-z0-9_.-] character in namespace {} in pack {}, ignoring", s, this.root);
                }
            }
        } catch (NotDirectoryException | NoSuchFileException nosuchfileexception) {
        } catch (IOException ioexception) {
            LOGGER.error("Failed to list path {}", path, ioexception);
        }

        return set;
    }

    @Override
    public void close() {
    }

    public static class PathResourcesSupplier implements Pack.ResourcesSupplier {
        private final Path content;
        private final boolean isBuiltin;

        public PathResourcesSupplier(Path p_294512_, boolean p_296311_) {
            this.content = p_294512_;
            this.isBuiltin = p_296311_;
        }

        @Override
        public PackResources openPrimary(String p_294153_) {
            return new PathPackResources(p_294153_, this.content, this.isBuiltin);
        }

        @Override
        public PackResources openFull(String p_295004_, Pack.Info p_294301_) {
            PackResources packresources = this.openPrimary(p_295004_);
            List<String> list = p_294301_.overlays();
            if (list.isEmpty()) {
                return packresources;
            } else {
                List<PackResources> list1 = new ArrayList<>(list.size());

                for(String s : list) {
                    Path path = this.content.resolve(s);
                    list1.add(new PathPackResources(p_295004_, path, this.isBuiltin));
                }

                return new CompositePackResources(packresources, list1);
            }
        }
    }
}
