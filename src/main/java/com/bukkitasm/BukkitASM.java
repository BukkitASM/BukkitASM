package com.bukkitasm;

import org.bukkit.event.Event;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitASM {
    public static String version = "1.0";
    public static String[] authors = {"King_Jasper", ""};

    //TODO: Handle the Core plugin's
    public void start() {

    }

    public static void craftInit() {
        System.out.println("*CraftInit*");
    }


    public static void onPreStartDedicatedServer() {
        System.out.println("[BukkitASM] Starting dedicated server!");
    }

    //TODO: Handle all event calls
    public static void onEventCall(Event event) {



    }
}
