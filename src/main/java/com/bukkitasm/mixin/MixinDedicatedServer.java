package com.bukkitasm.mixin;

import com.bukkitasm.BukkitASM;
import net.minecraft.server.v1_12_R1.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

/**
 * Created by Jasper on 3-10-2017.
 */
@Mixin(DedicatedServer.class)
public class MixinDedicatedServer {


    @Inject(method = "init", remap = false, at = @At("HEAD"))
    public void init(CallbackInfoReturnable<Boolean> callbackInfo) throws IOException {

        BukkitASM.onPreStartDedicatedServer((DedicatedServer) (Object) this);
    }



}
