package com.pinkcandy.mixin.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// 客户端MC逻辑修改器
@Mixin(Minecraft.class)
public class ExampleClientMixin {
	@Inject(at=@At("HEAD"),method="run")
	private void init(CallbackInfo info){}
}
