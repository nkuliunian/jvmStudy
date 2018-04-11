package jvm.eight_chapter.OperandStack;

/**
 * 方法动态分派演示
 * @author zzm
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}

    /*****   javap查看字节码
     D:\java\jdk1.8.0_161\bin\javap.exe -c jvm.eight_chapter.OperandStack.DynamicDispatch
     Compiled from "DynamicDispatch.java"
     public class jvm.eight_chapter.OperandStack.DynamicDispatch {
     public jvm.eight_chapter.OperandStack.DynamicDispatch();
     Code:
     0: aload_0
     1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     4: return

     public static void main(java.lang.String[]);
     Code:
     0: new           #2                  // class jvm/eight_chapter/OperandStack/DynamicDispatch$Man
     3: dup
     4: invokespecial #3                  // Method jvm/eight_chapter/OperandStack/DynamicDispatch$Man."<init>":()V
     7: astore_1
     8: new           #4                  // class jvm/eight_chapter/OperandStack/DynamicDispatch$Woman
     11: dup
     12: invokespecial #5                  // Method jvm/eight_chapter/OperandStack/DynamicDispatch$Woman."<init>":()V
     15: astore_2
     16: aload_1
     17: invokevirtual #6                  // Method jvm/eight_chapter/OperandStack/DynamicDispatch$Human.sayHello:()V
     20: aload_2
     21: invokevirtual #6                  // Method jvm/eight_chapter/OperandStack/DynamicDispatch$Human.sayHello:()V
     24: new           #4                  // class jvm/eight_chapter/OperandStack/DynamicDispatch$Woman
     27: dup
     28: invokespecial #5                  // Method jvm/eight_chapter/OperandStack/DynamicDispatch$Woman."<init>":()V
     31: astore_1
     32: aload_1
     33: invokevirtual #6                  // Method jvm/eight_chapter/OperandStack/DynamicDispatch$Human.sayHello:()V
     36: return
     }
     * *****/
