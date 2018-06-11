import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的三种方式
 */
public class ThreadStudy {

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("继承Thread的创建方式，当前线程："+currentThread().getName());
        }
    }

    static class MyThread2 implements Runnable{

        @Override
        public void run() {
            System.out.println("实现Runnable接口的创建方式，当前线程：" + Thread.currentThread().getName());
        }
    }

    static class MyThread3 implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("实现Callable接口的创建方式，当前线程：" + Thread.currentThread().getName());
            return "创建成功";
        }
    }

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        MyThread3 myThread3 = new MyThread3();

        myThread1.run();
        myThread2.run();
        try {
            Object rst = myThread3.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        myThread1.start();
        new Thread(myThread2).start();
        FutureTask<String> ft = new FutureTask<>(myThread3);
        new Thread(ft).start();
        try {
            String rst = ft.get();
            System.out.println(rst);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
