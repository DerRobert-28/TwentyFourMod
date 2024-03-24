package net.minecraft.world.level.block.state.properties;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.SoundType;

public record WoodType(
    String name, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen
) {
    private static final Map<String, WoodType> TYPES = new Object2ObjectArrayMap<>();
    public static final Codec<WoodType> CODEC = ExtraCodecs.stringResolverCodec(WoodType::name, TYPES::get);
    public static final WoodType OAK = register(new WoodType("oak", BlockSetType.OAK));
    public static final WoodType SPRUCE = register(new WoodType("spruce", BlockSetType.SPRUCE));
    public static final WoodType BIRCH = register(new WoodType("birch", BlockSetType.BIRCH));
    public static final WoodType ACACIA = register(new WoodType("acacia", BlockSetType.ACACIA));
    public static final WoodType CHERRY = register(
        new WoodType(
            "cherry",
            BlockSetType.CHERRY,
            SoundType.CHERRY_WOOD,
            SoundType.CHERRY_WOOD_HANGING_SIGN,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN
        )
    );
    public static final WoodType JUNGLE = register(new WoodType("jungle", BlockSetType.JUNGLE));
    public static final WoodType DARK_OAK = register(new WoodType("dark_oak", BlockSetType.DARK_OAK));
    public static final WoodType CRIMSON = register(
        new WoodType(
            "crimson",
            BlockSetType.CRIMSON,
            SoundType.NETHER_WOOD,
            SoundType.NETHER_WOOD_HANGING_SIGN,
            SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
        )
    );
    public static final WoodType WARPED = register(
        new WoodType(
            "warped",
            BlockSetType.WARPED,
            SoundType.NETHER_WOOD,
            SoundType.NETHER_WOOD_HANGING_SIGN,
            SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
        )
    );
    public static final WoodType MANGROVE = register(new WoodType("mangrove", BlockSetType.MANGROVE));
    public static final WoodType BAMBOO = register(
        new WoodType(
            "bamboo",
            BlockSetType.BAMBOO,
            SoundType.BAMBOO_WOOD,
            SoundType.BAMBOO_WOOD_HANGING_SIGN,
            SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN
        )
    );

    public WoodType(String p_273766_, BlockSetType p_273104_) {
        this(p_273766_, p_273104_, SoundType.WOOD, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
    }

    public static WoodType register(WoodType p_61845_) {
        TYPES.put(p_61845_.name(), p_61845_);
        return p_61845_;
    }

    public static Stream<WoodType> values() {
        return TYPES.values().stream();
    }
}
