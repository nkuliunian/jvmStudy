package thirteen_chapter;

import java.util.Vector;

/**
 * 绝对线程安全测试
 */
public class AbsoluteThreadSafetyTest {

    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println((vector.get(i)));
                    }
                }
            });

            removeThread.start();
            printThread.start();

            //不要同时产生过多的线程，否则会导致操作系统假死
            while (Thread.activeCount() > 20);
        }
    }
}

/***********************   运行时会抛异常：可以看出vector并不是绝对线程安全的
 Exception in thread "Thread-17844" java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 9
 at java.util.Vector.remove(Vector.java:831)
 at thirteen_chapter.AbsoluteThreadSafetyTest$1.run(AbsoluteThreadSafetyTest.java:22)
 at java.lang.Thread.run(Thread.java:748)


 *********/