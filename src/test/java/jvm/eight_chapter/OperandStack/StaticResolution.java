package jvm.eight_chapter.OperandStack;

/**
 * 方法静态解析演示，使用javap命令
 *
 * @author zzm
 */
public class StaticResolution {


    public static void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}

    /**  javap执行结果：
     D:\java\jdk1.8.0_161\bin\javap.exe -c jvm.eight_chapter.OperandStack.StaticResolution
     Compiled from "StaticResolution.java"
     public class jvm.eight_chapter.OperandStack.StaticResolution {
     public jvm.eight_chapter.OperandStack.StaticResolution();
     Code:
     0: aload_0
     1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     4: return

     public static void sayHello();
     Code:
     0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     3: ldc           #3                  // String hello world
     5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     8: return

     public static void main(java.lang.String[]);
     Code:
     0: invokestatic  #5                  // Method sayHello:()V
     3: return
     }
     */
