package com.bukkitasm;

import com.bukkitasm.plugin.ILoadingData;
import com.bukkitasm.plugin.ILoadingPlugin;

/**
 * Created by Jasper on 4-10-2017.
 */
public class Test extends ILoadingPlugin {

    @Override
    public void preSetup() {
        setILoadingData(new ILoadingData("Test", "1.0"));
    }
}
