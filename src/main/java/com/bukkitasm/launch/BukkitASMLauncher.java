package com.bukkitasm.launch;

import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitASMLauncher {

    public static void main(String[] args ) {
        try {
            new BukkitASMLauncher().launch(args);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void launch(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {


        Path base = Paths.get(BukkitASMLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        if(!checkMinecraft(base)) {
            return;
        }
        Logger logger = Logger.getLogger("Minecraft");
        Launch.main(join(args,
                "--tweakClass", "com.bukkitasm.launch.BukkitASMTweaker"
        ));



    }



    private static String[] join(String[] args, String... prefix) {
        String[] result = new String[prefix.length + args.length];
        System.arraycopy(prefix, 0, result, 0, prefix.length);
        System.arraycopy(args, 0, result, prefix.length, args.length);
        return result;
    }

    private boolean checkMinecraft(Path base) {
    boolean toReturn = false;

        File serverFile = new File("server.jar");
        if(serverFile.exists()) {
            toReturn = true;
            base.resolve("server.jar");

        }

    return toReturn;
    }


}
