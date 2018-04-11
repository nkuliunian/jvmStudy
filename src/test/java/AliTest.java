import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 本方法是一个阿里云的面试题
 * 题目要求：1.两个线程，一个线程生产者，一个线程是消费者
 *          2.生产者生产票，超过10张就休息，被消费了就继续生产。
 *          3.消费者消费票，票没了之后就休息，有票了接着消费。
 * 考点：消息队列、线程、线程通信、锁
 */
public class AliTest {

    static String waitProp = new String();
    static MyQueue<String> q = new MyQueue<>();

    /**
     * 自定义一个Queue，实现先进后出
     * 其实LinkedList内部对queue的各个方法实现更精妙，可以直接只用，这里主要为了抽出几个关键方法和属性来表达Queue的基础原理
     */
    static class MyQueue<T> {
        //使用LinkedList作为Queue存放消息
        private LinkedList<T> storage = new LinkedList<T>();

        public synchronized void push(T e) {//需要加上同步
            storage.addLast(e);
        }

        public T peek() {
            T t = null;
            try {
                t = storage.getFirst();
            } catch (NoSuchElementException e) {}
            return t;
        }

        public void pop() {
            storage.removeFirst();
        }

        public boolean empty() {
            return storage.isEmpty();
        }

        public int size() {
            return storage.size();
        }

        @Override
        public String toString() {
            return storage.toString();
        }
    }

    //生产者线程
    static class Provider extends Thread {

        @Override
        public void run() {
            while (true) {
                q.push("piao");
                System.out.println("加票");
                //每次生产完了之后通知一下等待中的生产者
                synchronized (waitProp) {
                    waitProp.notifyAll();
                }
                if (q.size() >= 10) {
                    System.out.println("加票满了");

                    synchronized (waitProp) {
                        try {
                            waitProp.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //消费者线程
    static class Comsumer extends Thread {

        @Override
        public synchronized void run() {
            while (true) {
                String a = q.peek();
                System.out.println("取票");
                //每次消费完了之后通知一下等待中的生产者
                synchronized (waitProp) {
                    waitProp.notifyAll();
                }
                if (a == null) {
                    System.out.println("取票没了");

                    synchronized (waitProp) {
                        try {
                            waitProp.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        Provider p = new Provider();
        Comsumer c = new Comsumer();
        p.start();
        c.start();
    }
}