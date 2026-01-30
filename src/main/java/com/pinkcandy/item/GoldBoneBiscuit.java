package com.pinkcandy.item;

import java.util.function.Function;
import com.pinkcandy.Shinywolf;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

// 黄金骨头饼干
public class GoldBoneBiscuit {
    // MC物品
    public static final Item THE_ITEM = register("gold_bone_biscuit", 
        Item::new, 
        new Item.Properties()
            .food(new FoodProperties.Builder()
                .nutrition(4)
                .saturationModifier(0f)
                .build()
            )
    );
    // 初始化
    public static void initialize(){
        ItemGroupEvents
        .modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS) // 创造模式物品分类
        .register((itemGroup)->itemGroup.accept(THE_ITEM));
    }
    // 物品注册
	public static <GenericItem extends Item> GenericItem register(
        String name,
        Function<Item.Properties,GenericItem> itemFactory,
        Item.Properties settings
    ){
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(Shinywolf.MOD_ID,name));
		GenericItem item = itemFactory.apply(settings.setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM,itemKey,item);
		return item;
	}
}
