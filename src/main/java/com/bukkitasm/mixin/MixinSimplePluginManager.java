package com.bukkitasm.mixin;

import com.bukkitasm.BukkitASM;
import org.bukkit.event.Event;
import org.bukkit.plugin.SimplePluginManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Jasper on 3-10-2017.
 */
@Mixin(SimplePluginManager.class)
public class MixinSimplePluginManager {

    @Inject(method = "callEvent",  remap = false, at= @At("HEAD"))
    private void calLEvent(Event event, CallbackInfo callbackInfo) {
        BukkitASM.onEventCall(event);
    }


}


