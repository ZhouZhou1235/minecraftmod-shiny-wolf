package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.AbandonBone;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

// 遗弃骨头的行为
public class AbandonBoneHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack itemStack = player.getItemInHand(hand);
            // 手持 是驯服的狼
            if (!itemStack.is(AbandonBone.THE_ITEM) 
                || !(entity instanceof Wolf wolf) 
                || !wolf.isTame()) {
                return InteractionResult.PASS;
            }
            // 将驯服的狼变回野狼
            if (!world.isClientSide()){
                wolf.tame(null);
                wolf.setTame(false,false);
                wolf.setOrderedToSit(false);
                wolf.setHealth(wolf.getMaxHealth());
                wolf.setPersistentAngerTarget(null);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                Shinywolf.LOGGER.info(
                    "Player {} abandoned a tamed wolf with an abandon bone", 
                    player.getName().getString()
                );
            }
            return InteractionResult.SUCCESS;
        });
    }
}
