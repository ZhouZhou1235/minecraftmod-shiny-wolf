package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.PowerBone;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

// 力量骨头的行为
public class PowerBoneHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack itemStack = player.getItemInHand(hand);
            // 手持 是驯服的狼
            if (!itemStack.is(PowerBone.THE_ITEM) 
                || !(entity instanceof Wolf wolf) 
                || !wolf.isTame()) {
                return InteractionResult.PASS;
            }
            // 提升攻击和防御1分钟
            if (!world.isClientSide()) {
                wolf.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, 1));
                wolf.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 1200, 1));
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                Shinywolf.LOGGER.info(
                    "Player {} gave a power bone to a wolf", 
                    player.getName().getString()
                );
            }
            return InteractionResult.SUCCESS;
        });
    }
}
