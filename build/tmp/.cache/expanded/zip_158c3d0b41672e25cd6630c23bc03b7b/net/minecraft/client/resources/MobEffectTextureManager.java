package net.minecraft.client.resources;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MobEffectTextureManager extends TextureAtlasHolder {
    public MobEffectTextureManager(TextureManager p_118730_) {
        super(p_118730_, new ResourceLocation("textures/atlas/mob_effects.png"), new ResourceLocation("mob_effects"));
    }

    public TextureAtlasSprite get(MobEffect p_118733_) {
        return this.getSprite(BuiltInRegistries.MOB_EFFECT.getKey(p_118733_));
    }
}
