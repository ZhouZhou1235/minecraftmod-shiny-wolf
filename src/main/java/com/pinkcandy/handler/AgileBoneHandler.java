package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.AgileBone;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

// 敏捷骨头的行为
public class AgileBoneHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack itemStack = player.getItemInHand(hand);
            // 手持 是驯服的狼
            if (!itemStack.is(AgileBone.THE_ITEM) 
                || !(entity instanceof Wolf wolf) 
                || !wolf.isTame()) {
                return InteractionResult.PASS;
            }
            // 提高速度3分钟
            if (!world.isClientSide()) {
                wolf.addEffect(new MobEffectInstance(MobEffects.SPEED, 3600, 1));
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                Shinywolf.LOGGER.info(
                    "Player {} gave an agile bone to a wolf", 
                    player.getName().getString()
                );
            }
            return InteractionResult.SUCCESS;
        });
    }
}
