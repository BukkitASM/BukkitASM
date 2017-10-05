package com.bukkitasm.manager;

import com.bukkitasm.BukkitASM;
import com.bukkitasm.plugin.ILoadingPlugin;
import com.bukkitasm.transformer.ITransformer;

import java.util.ArrayList;

/**
 * Created by Jasper on 4-10-2017.
 */
public class TransformerManager {

    public ArrayList<ITransformer> transformers;

    public void init() {
        transformers = new ArrayList<ITransformer>();


        for(ILoadingPlugin plugin : BukkitASM.pluginManager.getPlugins()) {
            for(ITransformer transformer : plugin.getiLoadingData().getTransformers()) {
                transformers.add(transformer);
            }
        }

    }


}
