package com.bukkitasm.tesplugin;

import com.bukkitasm.transformer.ITransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
/**
 * Created by Jasper on 5-10-2017.
 */
public class BlockCactusTransformer extends ITransformer {

    public BlockCactusTransformer(String classTarget) {
        super(classTarget);
    }


    @Override
    public byte[] transform(String className, byte[] buffer) {

        ClassReader  classReader = new ClassReader(buffer);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);

        for(MethodNode method : classNode.methods) {
            if(method.name.equals("a")) {
                if(method.desc.equals("(Lnet/minecraft/server/v1_12_R1/World;Lnet/minecraft/server/v1_12_R1/BlockPosition;Lnet/minecraft/server/v1_12_R1/IBlockData;Lnet/minecraft/server/v1_12_R1/Entity;)V")) {

                    //Better to wrap this in a IFEQ, (Fore more info about ASM watch VikeStep his Videos on YT
                    System.out.println("[Removing cactusDamage!]");
                    method.instructions.insert(new InsnNode(Opcodes.RETURN));
                    LabelNode newLabelNode = new LabelNode();
                    method.instructions.insert(newLabelNode);

                }
            }


        }


        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(classWriter);
        return classWriter.toByteArray();


    }
}
