package com.bukkitasm.launch;

import com.bukkitasm.BukkitASM;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jasper on 2-10-2017.
 */
    public class BukkitASMTweaker implements ITweaker {

        public static InputStream in = System.in;
        public static OutputStream out = System.out;

        @Override
        public void acceptOptions(List<String> list, File file, File file1, String s) {

        }

        @Override
        public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
            BukkitASM.start();


            try {

                launchClassLoader.addURL(new File("server.jar").toURI().toURL());




                launchClassLoader.registerTransformer("com.bukkitasm.launch.transformer.BukkitTransformer");
                MixinBootstrap.init();
                MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
                env.setSide(MixinEnvironment.Side.SERVER);
                Mixins.addConfiguration("mixins.server.json");

            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.exit(1);
            }
            launchClassLoader.registerTransformer("com.bukkitasm.launch.transformer.PluginTransformer");
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
