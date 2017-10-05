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

                    AbstractInsnNode targetNode = null;

                    for(AbstractInsnNode instruction : method.instructions.toArray()) {
                        if(instruction.getOpcode() == Opcodes.GETSTATIC) {
                            if(instruction.getNext().getOpcode() == Opcodes.FCONST_1) {
                                if(instruction.getNext().getNext().getOpcode() == Opcodes.INVOKEVIRTUAL ) {
                                    targetNode = instruction;

                                }
                            }

                        }
                    }
                    if(targetNode !=null) {
                        System.out.println("[Removing cactusDamage!]");
                        targetNode = targetNode.getPrevious();
                        method.instructions.insertBefore(targetNode, new InsnNode(Opcodes.RETURN));
                        LabelNode newLabelNode = new LabelNode();
                        method.instructions.insert(targetNode, newLabelNode);

                    }


                }
            }


        }


        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(classWriter);
        return classWriter.toByteArray();


    }
}
