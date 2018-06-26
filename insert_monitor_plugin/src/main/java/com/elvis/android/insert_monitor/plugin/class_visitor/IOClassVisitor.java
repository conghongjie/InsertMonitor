package com.elvis.android.insert_monitor.plugin.class_visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * IO检测
 * 耗时检测／主线程检测
 * Created by conghongjie on 2018/6/22.
 */

public class IOClassVisitor extends ClassVisitor{


    //探测的函数：
    private static final List<String> dbInsertMethods = Arrays.asList(new String[]{
            "beginTransaction",
            "beginTransactionNonExclusive",
            "beginTransactionWithListener",
            "beginTransactionWithListenerNonExclusive",
            "create",
            "delete",
            "deleteDatabase",
            "execSQL",
            "insert",
            "insertOrThrow",
            "insertWithOnConflict",
            "query",
            "queryWithFactory",
            "rawQuery",
            "rawQueryWithFactory",
            "replace",
            "replaceOrThrow",
            "update",
            "updateWithOnConflict"
    });
    private static boolean isInsertMethod(String method) {
        return dbInsertMethods.contains(method);
    }

    // 忽略的包名：
    List<String> noDetctPackages;
    // 此Class是否忽略
    boolean isThisClassNoDetect = false;

    public IOClassVisitor(ClassVisitor classVisitor, List<String> noDetectPackages) {
        super(Opcodes.ASM5, classVisitor);
        this.noDetctPackages = noDetectPackages;
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        isThisClassNoDetect = false;
        if(this.noDetctPackages != null) {
            Iterator iterator = this.noDetctPackages.iterator();
            while(iterator.hasNext()) {
                String pack = (String)iterator.next();
                if(name.contains(pack)) {
                    this.isThisClassNoDetect = true;
                    break;
                }
            }
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if(this.isThisClassNoDetect) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        } else {
            final MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
            AdviceAdapter mv1 = new AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {
                public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean b) {
                    if(owner.equals("android/database/sqlite/SQLiteDatabase") && IOClassVisitor.isInsertMethod(name)) {
                        this.mv.visitLdcInsn(name+"sss");
                        this.mv.visitMethodInsn(INVOKESTATIC, "com/elvis/android/insert_monitor/plugin/insert/IOInsert", "onDBEvent", "(Ljava/lang/String;)V", false);
                    }
                    super.visitMethodInsn(opcode, owner, name, desc, b);
                }
            };
            return mv1;
        }
    }





}
