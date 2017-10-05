package com.bukkitasm;

import com.bukkitasm.manager.IPluginManager;
import com.bukkitasm.manager.TransformerManager;
import net.minecraft.server.v1_12_R1.DedicatedServer;
import org.bukkit.event.Event;

import java.io.File;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitASM {
    public static String version = "1.0";
    public static String[] authors = {"King_Jasper", ""};
    public static IPluginManager pluginManager;
    public static TransformerManager transformerManager;
    //TODO: Handle the Core plugin's
    public static void start() {
        File pluginDir = new File("corePlugins");
        if(!pluginDir.exists()) {
            pluginDir.mkdirs();
        }

        pluginManager = new IPluginManager(pluginDir);
        pluginManager.initPluginManager();
        transformerManager = new TransformerManager();
        transformerManager.init();
    }

    public static void craftInit() {
        System.out.println("*CraftInit*");
    }


    public static void onPreStartDedicatedServer(DedicatedServer server) {
        System.out.println("[BukkitASM] Starting dedicated server version: " + server.getVersion());
    }

    //TODO: Handle all event calls
    public static void onEventCall(Event event) {



    }
}
