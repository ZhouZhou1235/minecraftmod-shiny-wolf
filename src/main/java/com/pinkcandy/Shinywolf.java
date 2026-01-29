package com.pinkcandy;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pinkcandy.item.GoldBone;

// 模组加载器
public class Shinywolf implements ModInitializer {
	public static final String MOD_ID = "shiny-wolf"; // 模组号码
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID); // 日志记录器
	// 开始加载模组
	@Override
	public void onInitialize(){
		LOGGER.info("minecraft mod shiny-wolf by pinkcandyzhou");
		// 物品注册
		GoldBone.initialize();
	}
}
