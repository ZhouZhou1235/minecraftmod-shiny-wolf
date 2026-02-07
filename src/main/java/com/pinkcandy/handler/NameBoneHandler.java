package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.NameBone;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

// 命名骨头的行为
public class NameBoneHandler {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack itemStack = player.getItemInHand(hand);
            // 手持 是驯服的狼
            if (!itemStack.is(NameBone.THE_ITEM) 
                || !(entity instanceof Wolf wolf) 
                || !wolf.isTame()) {
                return InteractionResult.PASS;
            }
            // 骨头右键可以命名驯服的狼
            if (itemStack.getCustomName()!=null){
                if (!world.isClientSide()) {
                    Component boneName = itemStack.getHoverName();
                    String wolfName = boneName.getString();
                    wolf.setCustomName(boneName);
                    wolf.setCustomNameVisible(false);
                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                    Shinywolf.LOGGER.info(
                        "Player {} named a wolf '{}' with a name bone", 
                        player.getName().getString(),
                        wolfName
                    );
                }
                return InteractionResult.SUCCESS;
            }
            else {
                if (world.isClientSide()) {
                    player.displayClientMessage(
                        Component.literal("please rename name bone first").withStyle(ChatFormatting.YELLOW),
                        true
                    );
                }
                return InteractionResult.PASS;
            }
        });
    }
}
