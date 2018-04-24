package nine_chapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestClass {

    public static void hahaha(String[] args) {
        System.out.println("测试一下哈哈哈哈");
    }
}

class TestClass1{
    public static void main(String[] args) throws IOException {
        System.out.println("测试一下远程修改类并实现热替换");
        InputStream is = new FileInputStream("D:\\java\\study-git\\target\\classes\\nine_chapter\\TestClass.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();

        System.out.println("#################################################");
        System.out.println(JavaClassExecuter.execute(b));
        System.out.println("#################################################");
    }
}


/***************这个类的字节码***********
 D:\java\jdk1.8.0_161\bin\javap.exe -c nine_chapter.TestClass
 Compiled from "TestClass.java"
 public class nine_chapter.TestClass {
 public nine_chapter.TestClass();
 Code:
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return

 public static void hahaha(java.lang.String[]);
 Code:
 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 3: ldc           #3                  // String 测试一下哈哈哈哈
 5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 8: return
 }
 ***************/