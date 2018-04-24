package eight_chapter;

//局部变量表
public class LocalVariableTable {

    //局部变量表Slot复用对垃圾收集的影响,加上“-verbose:gc”查看垃圾收集过程
    public static void main(String[] args) throws Exception {
        //不会回收
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();

        Thread.sleep(1000);
        //不会回收
        {
            byte[] placeholder2 = new byte[64 * 1024 * 1024];
        }
        System.gc();

        //会回收
        {
            byte[] placeholder3 = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();

        //没有被赋初始值的局部变量是不能使用的，不会默认为0，但以下代码编译有可能不会报错，类加载的时候字节码校验也会失败
        int b;
//        System.out.println(b);
    }
}
/**********
 *   执行结果：可以看出第三次gc的时候，只剩下64M的内存占用了，就是说placeholder2和placeholder3都会被回收
 [GC (System.gc())  68174K->66448K(123904K), 0.0010648 secs]
 [Full GC (System.gc())  66448K->66273K(123904K), 0.0059660 secs]
 [GC (System.gc())  131809K->131809K(189952K), 0.0032124 secs]
 [Full GC (System.gc())  131809K->131799K(189952K), 0.0358375 secs]
 [GC (System.gc())  197991K->197335K(256000K), 0.0009533 secs]
 [Full GC (System.gc())  197335K->66263K(256000K), 0.0080337 secs]
 *
 * **********/
