package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.GoldBoneBiscuit;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

// 黄金骨头饼干的行为
public class GoldBoneBiscuitUsageHandler {
    public static void register(){
        UseEntityCallback.EVENT.register((player,world,hand,entity,hitResult)->{
            ItemStack itemStack = player.getItemInHand(hand);
            // 手持 是驯服的狼
            if(
                !itemStack.is(GoldBoneBiscuit.THE_ITEM)
                ||
                !(entity instanceof Wolf wolf)
                ||
                !wolf.isTame()
            ){return InteractionResult.PASS;}
            // 喂食
            // 回满狼血量，发光一分钟。
            if(!world.isClientSide()){
                wolf.setHealth(wolf.getMaxHealth());
                wolf.addEffect(new MobEffectInstance(MobEffects.GLOWING,1200,0));
                if(!player.getAbilities().instabuild){itemStack.shrink(1);}
                Shinywolf.LOGGER.info(
                    "Player {} fed a gold bone biscuit to a wolf, healing it to full health", 
                    player.getName().getString()
                );
            }
            return InteractionResult.SUCCESS;
        });
    }
}
