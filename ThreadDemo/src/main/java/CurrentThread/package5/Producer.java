package CurrentThread.package5;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:sgyt
 * @Description:生产者线程
 * @Date:2019/3/2 13:53
 */
public class Producer implements Runnable{
    private final BlockingQueue queue;

    private static final int      DEFAULT_RANGE_FOR_SLEEP = 1000;
    private volatile boolean      isRunning = true;
//    线程安全类，atomicInteger
    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
       String data = null;
        Random r = new Random();
        System.out.println("启动生成者错误");

        try{
            while(isRunning){
                System.out.println("正在生成数据");
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                data = "data:"+count.incrementAndGet();
                System.out.println("讲数据："+data+"放入队列中");
                if(!queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("放入数据失败");
                }
            }

        }catch (InterruptedException e){
            e.printStackTrace();
//            这个线程中断有意义吗，如果这里中断了，那么线程怎么再次执行
            Thread.currentThread().interrupt();
        }finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }


}
