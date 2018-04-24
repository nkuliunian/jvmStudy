package eight_chapter.dynamicLanguageSupport;

import java.lang.invoke.*;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * invokeDynamic指令演示
 */
public class InvokeDynamicTest {

    public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("icyfenix");
    }

    public static void testMethod(String s) {
        System.out.println("hello String:" + s);
    }

    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable {
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
    }

    private static MethodType MT_BootstrapMethod() {
        return MethodType
                .fromMethodDescriptorString(
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                        null);
    }

    private static MethodHandle MH_BootstrapMethod() throws Throwable {
        return lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite cs = (CallSite) MH_BootstrapMethod().invokeWithArguments(lookup(), "testMethod",
                MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return cs.dynamicInvoker();
    }
}
/***********
 执行结果：
 hello String:icyfenix
 ******************/
/************  javap查看字节码
 D:\java\jdk1.8.0_161\bin\javap.exe -c InvokeDynamicTest
 Compiled from "InvokeDynamicTest.java"
 public class InvokeDynamicTest {
 public InvokeDynamicTest();
 Code:
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return

 public static void main(java.lang.String[]) throws java.lang.Throwable;
 Code:
 0: invokestatic  #2                  // Method INDY_BootstrapMethod:()Ljava/lang/invoke/MethodHandle;
 3: ldc           #3                  // String icyfenix
 5: invokevirtual #4                  // Method java/lang/invoke/MethodHandle.invokeExact:(Ljava/lang/String;)V
 8: return

 public static void testMethod(java.lang.String);
 Code:
 0: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
 3: new           #6                  // class java/lang/StringBuilder
 6: dup
 7: invokespecial #7                  // Method java/lang/StringBuilder."<init>":()V
 10: ldc           #8                  // String hello String:
 12: invokevirtual #9                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 15: aload_0
 16: invokevirtual #9                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 19: invokevirtual #10                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 22: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 25: return

 public static java.lang.invoke.CallSite BootstrapMethod(java.lang.invoke.MethodHandles$Lookup, java.lang.String, java.lang.invoke.MethodType) throws java.lang.Throwable;
 Code:
 0: new           #12                 // class java/lang/invoke/ConstantCallSite
 3: dup
 4: aload_0
 5: ldc           #13                 // class jvm/eight_chapter/dynamicLanguageSupport/InvokeDynamicTest
 7: aload_1
 8: aload_2
 9: invokevirtual #14                 // Method java/lang/invoke/MethodHandles$Lookup.findStatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/MethodHandle;
 12: invokespecial #15                 // Method java/lang/invoke/ConstantCallSite."<init>":(Ljava/lang/invoke/MethodHandle;)V
 15: areturn
 }
 */
