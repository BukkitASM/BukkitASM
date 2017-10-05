package com.bukkitasm;

import com.bukkitasm.plugin.ILoadingData;
import com.bukkitasm.plugin.ILoadingPlugin;
import net.minecraft.server.v1_12_R1.BlockCactus;

/**
 * Created by Jasper on 4-10-2017.
 */
public class Test extends ILoadingPlugin {

    @Override
    public void preSetup() {

        ILoadingData loadingData = new ILoadingData("TestPlugin", "1.0");

        loadingData.getTransformers().add(new TestTransformer("net.minecraft.server.v1_12_R1.BlockCactus"));

        setILoadingData(loadingData);
    }
}
