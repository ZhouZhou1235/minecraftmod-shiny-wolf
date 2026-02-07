package com.pinkcandy.handler;

import com.pinkcandy.Shinywolf;
import com.pinkcandy.item.GoldBone;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

// 黄金骨头的行为
public class GoldBoneHandler {
    public static void register(){
        UseEntityCallback.EVENT.register((player,world,hand,entity,hitResult)->{
            ItemStack itemStack = player.getItemInHand(hand);
            // 手持 是驯服的狼
            if(
                !itemStack.is(GoldBone.THE_ITEM)
                ||
                !(entity instanceof Wolf wolf)
                ||
                !wolf.isTame()
            ){return InteractionResult.PASS;}
            // 喂食
            // 恢复一半血量，发光7分钟。
            if(!world.isClientSide()){
                float halfHealth = wolf.getMaxHealth() / 2.0f;
                float newHealth = wolf.getHealth() + halfHealth;
                wolf.setHealth(Math.min(newHealth, wolf.getMaxHealth()));
                wolf.addEffect(new MobEffectInstance(MobEffects.GLOWING,1200*7,0));
                if(!player.getAbilities().instabuild){itemStack.shrink(1);}
                Shinywolf.LOGGER.info(
                    "Player {} fed a gold bone to a wolf", 
                    player.getName().getString()
                );
            }
            return InteractionResult.SUCCESS;
        });
    }
}
