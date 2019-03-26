package CurrentThread.package5;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/2 11:27
 */
public class BlockTest {
    private static final BlockingQueue<Integer> block = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        ScheduledExecutorService product = Executors.newScheduledThreadPool(1);
        Random random = new Random();

        product.scheduleAtFixedRate(()->{

        }, 0, 200, TimeUnit.MILLISECONDS);

        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(2000);
                    System.out.println("开始取值");
                    List<Integer> list = new LinkedList<>();
                    block.drainTo(list);
                    list.forEach(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
