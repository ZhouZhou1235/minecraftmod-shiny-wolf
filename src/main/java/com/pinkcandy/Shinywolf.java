package com.pinkcandy;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pinkcandy.handler.AgileBoneHandler;
import com.pinkcandy.handler.GoldBoneHandler;
import com.pinkcandy.handler.LoveBoneHandler;
import com.pinkcandy.handler.NameBoneHandler;
import com.pinkcandy.handler.ObedientBoneHandler;
import com.pinkcandy.handler.PowerBoneHandler;
import com.pinkcandy.item.AgileBone;
import com.pinkcandy.item.GoldBone;
import com.pinkcandy.item.LoveBone;
import com.pinkcandy.item.NameBone;
import com.pinkcandy.item.ObedientBone;
import com.pinkcandy.item.PowerBone;

// 模组加载器
public class Shinywolf implements ModInitializer {
	public static final String MOD_ID = "shiny-wolf"; // 模组号码
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID); // 日志记录器
	// 开始加载模组
	@Override
	public void onInitialize(){
		LOGGER.info("minecraft mod shiny-wolf by pinkcandyzhou");
		// 物品注册
		AgileBone.initialize();
		GoldBone.initialize();
		LoveBone.initialize();
		NameBone.initialize();
		ObedientBone.initialize();
		PowerBone.initialize();
		// 事件处理
		AgileBoneHandler.register();
		GoldBoneHandler.register();
		LoveBoneHandler.register();
		NameBoneHandler.register();
		ObedientBoneHandler.register();
		PowerBoneHandler.register();
	}
}
