package com.bukkitasm.launch;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitASMTweaker implements ITweaker {

    @Override
    public void acceptOptions(List<String> list, File file, File file1, String s) {

    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {

        try {
            launchClassLoader.addURL(new File("server.jar").toURI().toURL());
            launchClassLoader.registerTransformer("com.bukkitasm.launch.transformer.BukkitTransformer");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLaunchTarget() {
        return "org.bukkit.craftbukkit.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        ArrayList<String> arrayList = new ArrayList<String>();

        return arrayList.toArray(new String[arrayList.size()]);
    }

}
