package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.LoveBone;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

// 爱之骨头的行为
public class LoveBoneHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack itemStack = player.getItemInHand(hand);
            // 手持 是已驯服的狼
            if (!itemStack.is(LoveBone.THE_ITEM)
                || !(entity instanceof Wolf wolf)
                || !wolf.isTame()) {
                return InteractionResult.PASS;
            }
            // 立即使狼准备好涩涩
            if (!world.isClientSide()){
                if(wolf.getAge()>0 && wolf.canFallInLove()){
                    wolf.setHealth(wolf.getMaxHealth());
                    wolf.setAge(0);
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
                        if (!player.getAbilities().instabuild) {
                            itemStack.shrink(1);
                        }
                        Shinywolf.LOGGER.info(
                            "Player {} use love bone to wolf '{}'", 
                            player.getName().getString(),
                            wolf
                        );
                    }
                }
            }
            return InteractionResult.SUCCESS;
        });
    }
}
