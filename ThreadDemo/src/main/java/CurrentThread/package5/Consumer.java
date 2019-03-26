package CurrentThread.package5;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:消费者线程
 * @Date:2019/3/2 14:56
 */
public class Consumer implements Runnable {
    private static final int      DEFAULT_RANGE_FOR_SLEEP = 1000;
    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("启动消费者线程");
        Random r = new Random();
        boolean isRunning = true;
        try {
            while(isRunning){
                System.out.println("正从队列获取数据。。。。");
                String data = queue.poll(2, TimeUnit.SECONDS);
                if(null != data){
                    System.out.println("拿到数据"+data);
                    System.out.println("正在消费数据"+data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
