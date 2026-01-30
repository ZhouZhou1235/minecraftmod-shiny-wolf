package com.pinkcandy.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// MC逻辑修改器
@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"),method="loadLevel")
	private void init(CallbackInfo info){}
}
