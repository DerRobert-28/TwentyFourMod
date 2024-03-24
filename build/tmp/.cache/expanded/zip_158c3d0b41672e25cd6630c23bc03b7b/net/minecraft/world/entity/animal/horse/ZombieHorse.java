package net.minecraft.world.entity.animal.horse;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class ZombieHorse extends AbstractHorse {
    public ZombieHorse(EntityType<? extends ZombieHorse> p_30994_, Level p_30995_) {
        super(p_30994_, p_30995_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createBaseHorseAttributes().add(Attributes.MAX_HEALTH, 15.0).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    public static boolean checkZombieHorseSpawnRules(
        EntityType<? extends Animal> p_312450_, LevelAccessor p_312340_, MobSpawnType p_312031_, BlockPos p_311838_, RandomSource p_312514_
    ) {
        if (!MobSpawnType.isSpawner(p_312031_)) {
            return Animal.checkAnimalSpawnRules(p_312450_, p_312340_, p_312031_, p_311838_, p_312514_);
        } else {
            return MobSpawnType.ignoresLightRequirements(p_312031_) || isBrightEnoughToSpawn(p_312340_, p_311838_);
        }
    }

    @Override
    protected void randomizeAttributes(RandomSource p_218823_) {
        this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(generateJumpStrength(p_218823_::nextDouble));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_HORSE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_HORSE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_31006_) {
        return SoundEvents.ZOMBIE_HORSE_HURT;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_149561_, AgeableMob p_149562_) {
        return EntityType.ZOMBIE_HORSE.create(p_149561_);
    }

    @Override
    public InteractionResult mobInteract(Player p_31001_, InteractionHand p_31002_) {
        return !this.isTamed() ? InteractionResult.PASS : super.mobInteract(p_31001_, p_31002_);
    }

    @Override
    protected void addBehaviourGoals() {
    }

    @Override
    protected float getPassengersRidingOffsetY(EntityDimensions p_295879_, float p_294675_) {
        return p_295879_.height - (this.isBaby() ? 0.03125F : 0.28125F) * p_294675_;
    }
}
