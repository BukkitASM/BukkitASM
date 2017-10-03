package com.bukkitasm.launch.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitClassVisitor extends ClassVisitor {

    String className;

    public BukkitClassVisitor(ClassWriter classWriter, String className) {
        super(Opcodes.ASM5, classWriter);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        return new BukkitMethodVisitor(className,super.visitMethod(access, name, desc, signature, exceptions),name);
    }
}
