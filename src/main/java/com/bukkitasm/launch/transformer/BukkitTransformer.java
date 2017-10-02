package com.bukkitasm.launch.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String s, String s1, byte[] bytes) {
        //System.out.println("[Transformer] " + s);

        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader , 1);
        reader.accept((ClassVisitor)new BukkitClassVisitor(writer, s), 0);
        return  writer.toByteArray();

    }
}
