import java.util.Random;
import java.util.concurrent.*;

public class Test {
}

class BarrierDemo {
   public static void main(String[] args) {
       ExecutorService service = Executors.newFixedThreadPool(5);
       final CyclicBarrier barrier = new CyclicBarrier(4);
       for (int i = 0; i < 5; i++) {
           service.execute(new Player("玩家" + i, barrier));
       }
       service.shutdown();
   }
}

class Player implements Runnable {
   private final String name;
   private final CyclicBarrier barrier;

   public Player(String name, CyclicBarrier barrier) {
       this.name = name;
       this.barrier = barrier;
   }

   public void run() {
       try {
           TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
           System.out.println(name + "已准备,等待其他玩家准备...");
           barrier.await();
           TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
           System.out.println(name + "已加入游戏");
       } catch (InterruptedException e) {
           System.out.println(name + "离开游戏");
       } catch (BrokenBarrierException e) {
           System.out.println(name + "离开游戏");
       }

   }
}