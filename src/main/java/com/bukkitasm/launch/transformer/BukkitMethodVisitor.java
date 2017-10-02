package com.bukkitasm.launch.transformer;

import net.minecraft.server.v1_12_R1.CommandOp;
import org.bukkit.Bukkit;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitMethodVisitor extends MethodVisitor implements Opcodes {

    private String className;
    private String methodName;


    public BukkitMethodVisitor(String className, MethodVisitor methodVisitor, String methodName) {
        super(ASM5, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void visitLdcInsn(Object cst) {
        Object returnObject = cst;
        if(cst instanceof  String) {
            String string = String.valueOf(cst);
            //System.out.println(className + " String: " +  string);

            if(string.equals("multiplayer.player.joined")) {
                returnObject = String.valueOf("[BukkitASM] A player joined the Game!");
                System.out.println("[BukkitASM] Join message changed!");
            }


            if(className.contains("CommandOp")) {
                if(string.equals("op")) {
                    returnObject = String.valueOf("adm");
                    System.out.println("[BukkitASM] OpCommand changes to adm !");
                }
            }



        }

        super.visitLdcInsn(returnObject);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {

        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    @Override
    public void visitCode() {

        super.visitCode();
    }
}
