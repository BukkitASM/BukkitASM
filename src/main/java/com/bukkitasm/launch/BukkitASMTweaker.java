package com.bukkitasm.launch;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

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
            MixinBootstrap.init();
            MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
            env.setSide(MixinEnvironment.Side.SERVER);
            Mixins.addConfiguration("mixins.server.json");



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
