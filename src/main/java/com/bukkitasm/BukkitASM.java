package com.bukkitasm;

import net.minecraft.server.v1_12_R1.DedicatedServer;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitASM {
    public static String version = "1.0";
    public static String[] authors = {"King_Jasper", ""};

    //TODO: Handle the Core plugin's
    public void start() {

    }

    public static void onPreStartDedicatedServer() {
        System.out.println("[BukkitASM] Starting dedicated server!");
    }



}
