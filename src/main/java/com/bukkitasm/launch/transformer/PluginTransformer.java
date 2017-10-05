package com.bukkitasm.launch.transformer;

import com.bukkitasm.BukkitASM;
import com.bukkitasm.transformer.ITransformer;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 * Created by Jasper on 5-10-2017.
 */
public class PluginTransformer implements IClassTransformer{

    @Override
    public byte[] transform(String clzzName, String s1, byte[] buffer) {

        ///System.out.println(clzzName);

        byte[] modBytes = buffer;

    for(ITransformer transformer : BukkitASM.transformerManager.transformers) {

        if(clzzName.contains(transformer.classTarget)) {
            System.out.println("[ITransformer] transforming " + clzzName + " From: " + transformer.getClass().getName());
            modBytes = transformer.transform(clzzName, modBytes);
        }
    }



        return modBytes;
    }
}
