package com.bukkitasm.launch;

import com.bukkitasm.ConsoleWriter;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.logging.Logger;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitASMLauncher {

    public static void main(String[] args) {
        try {

            File serverJar = new File("server.jar");
            if (!serverJar.exists()) {
                System.err.println("You need a Spigot or bukkit jar in this folder and name it server.jar !");
                System.exit(1);
            }

            new BukkitASMLauncher().launch(args);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static String[] join(String[] args, String... prefix) {
        String[] result = new String[prefix.length + args.length];
        System.arraycopy(prefix, 0, result, 0, prefix.length);
        System.arraycopy(args, 0, result, prefix.length, args.length);
        return result;
    }

    public void launch(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {

        Logger logger = Logger.getLogger("Minecraft");
        Launch.main(join(args,
                "--tweakClass", "com.bukkitasm.launch.BukkitASMTweaker"
        ));

    }


}
