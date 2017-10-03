package com.bukkitasm.mixin;

import org.bukkit.craftbukkit.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Jasper on 3-10-2017.
 */
@Mixin(Main.class)
public class MixinMain {

    @Inject(method = "main",remap = false, at = @At("TAIL"))
    private static void main(String[] args, CallbackInfo callbackInfo) {
        System.out.println("[BukkitASM] Injected this using Mixins!");
    }
}
