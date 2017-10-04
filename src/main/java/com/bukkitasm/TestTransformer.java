package com.bukkitasm;

import com.bukkitasm.transformer.ITransformer;

/**
 * Created by Jasper on 4-10-2017.
 */
public class TestTransformer extends ITransformer {
    @Override
    public byte[] transform(String className, byte[] buffer) {



        return buffer;
    }
}
