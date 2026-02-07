package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.ObedientBone;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class ObedientBoneHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack itemStack = player.getItemInHand(hand);
            if (!itemStack.is(ObedientBone.THE_ITEM) || !(entity instanceof Wolf wolf)) {
                return InteractionResult.PASS;
            }
            if (!world.isClientSide()){
                wolf.setHealth(wolf.getMaxHealth());
                if(!wolf.isTame()){
                    wolf.tame(player);
                    if (world instanceof ServerLevel serverLevel) {
                        double offsetX = wolf.getRandom().nextGaussian() * 0.02;
                        double offsetY = wolf.getRandom().nextGaussian() * 0.02;
                        double offsetZ = wolf.getRandom().nextGaussian() * 0.02;
                        serverLevel.sendParticles(
                            ParticleTypes.HEART,
                            wolf.getX(),
                            wolf.getY() + 1.0,
                            wolf.getZ(),
                            7,
                            offsetX, offsetY, offsetZ,
                            0.5
                        );
                    }
                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                    Shinywolf.LOGGER.info(
                        "Player {} tamed a wild wolf with an obedient bone", 
                        player.getName().getString()
                    );
                }
            }
            return InteractionResult.SUCCESS;
        });
    }
}