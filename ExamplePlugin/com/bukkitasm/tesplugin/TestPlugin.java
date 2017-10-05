package com.bukkitasm.tesplugin;

import com.bukkitasm.plugin.ILoadingData;
import com.bukkitasm.plugin.ILoadingPlugin;

/**
 * Created by Jasper on 5-10-2017.
 */
public class TestPlugin extends ILoadingPlugin{

    @Override
    public void preSetup() {
        ILoadingData loadingData = new ILoadingData("TestPlugin", "1.0");
        loadingData.getTransformers().add(new BlockCactusTransformer("net.minecraft.server.v1_12_R1.BlockCactus"));
        setILoadingData(loadingData);
    }
}
