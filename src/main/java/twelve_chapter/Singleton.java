package twelve_chapter;

/**
 * DCL单例模式
 */
public class Singleton {

    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}

/******************** 查看singleton类的字节码，
 D:\java\jdk1.8.0_161\bin\javap.exe -c twelve_chapter.Singleton
 Compiled from "Singleton.java"
 public class twelve_chapter.Singleton {
 public twelve_chapter.Singleton();
 Code:
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return

 public static twelve_chapter.Singleton getInstance();
 Code:
 0: getstatic     #2                  // Field instance:Ltwelve_chapter/Singleton;
 3: ifnonnull     37
 6: ldc           #3                  // class twelve_chapter/Singleton
 8: dup
 9: astore_0
 10: monitorenter
 11: getstatic     #2                  // Field instance:Ltwelve_chapter/Singleton;
 14: ifnonnull     27
 17: new           #3                  // class twelve_chapter/Singleton
 20: dup
 21: invokespecial #4                  // Method "<init>":()V
 24: putstatic     #2                  // Field instance:Ltwelve_chapter/Singleton;
 27: aload_0
 28: monitorexit
 29: goto          37
 32: astore_1
 33: aload_0
 34: monitorexit
 35: aload_1
 36: athrow
 37: getstatic     #2                  // Field instance:Ltwelve_chapter/Singleton;
 40: areturn
 Exception table:
 from    to  target type
 11    29    32   any
 32    35    32   any

 public static void main(java.lang.String[]);
 Code:
 0: invokestatic  #5                  // Method getInstance:()Ltwelve_chapter/Singleton;
 3: pop
 4: return
 }




 */
