package com.bukkitasm.launch.transformer;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Jasper on 2-10-2017.
 */
public class BukkitMethodVisitor extends MethodVisitor implements Opcodes {

    private String className;
    private String methodName;

    private boolean injectOut = true;

    public BukkitMethodVisitor(String className, MethodVisitor methodVisitor, String methodName) {
        super(ASM5, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void visitLdcInsn(Object cst) {
        Object returnObject = cst;
        if (cst instanceof String) {
            String string = String.valueOf(cst);
            //System.out.println(className + " String: " +  string);

            if (string.equals("multiplayer.player.joined")) {
                returnObject = String.valueOf("[BukkitASM] A player joined the Game!");
                System.out.println("[BukkitASM] Join message changed!");
            }

            if (className.contains("CommandOp")) {
                if (string.equals("op")) {
                    returnObject = String.valueOf("adm");
                    System.out.println("[BukkitASM] OpCommand changes to adm !");
                }
            }
        }

        super.visitLdcInsn(returnObject);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {

        if(className.contains("craftServer")) {
            if(methodName.contains("<init>")) {


                if(injectOut) {
                    injectOut = false;
                    System.out.println("[BukkitASM] CraftServer init method found!, Injecting hooks.");
                    injectCraftInit(mv);
                    injectOutHook(mv);
                }

            }
        }

        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
    //TODO: Sadly this isnt working :W
    private void injectOutHook(MethodVisitor mv) {

        mv.visitTypeInsn(NEW, "java/io/PrintStream");
        mv.visitInsn(DUP);
        mv.visitFieldInsn(GETSTATIC, "com/bukkitasm/launch/BukkitASMTweaker", "out", "Ljava/io/OutputStream;");
        mv.visitMethodInsn(INVOKESPECIAL, "java/io/PrintStream", "<init>", "(Ljava/io/OutputStream;)V", false);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "setOut", "(Ljava/io/PrintStream;)V", false);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLineNumber(15, l1);
        mv.visitFieldInsn(GETSTATIC, "com/bukkitasm/launch/BukkitASMTweaker", "in", "Ljava/io/InputStream;");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "setIn", "(Ljava/io/InputStream;)V", false);
        Label l2 = new Label();
        mv.visitLabel(l2);
    }
    //TODO: Handle craftInit
    private void injectCraftInit(MethodVisitor mv) {
        mv.visitMethodInsn(INVOKESTATIC, "com/bukkitasm/BukkitASM", "craftInit", "()V", false);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        //System.out.println(" CLAZZ: " + className + " Method: " + methodName + " " +  "line = [" + line + "], start = [" + start + "]");

        super.visitLineNumber(line, start);
    }

    @Override
    public void visitCode() {

        super.visitCode();
    }
}
