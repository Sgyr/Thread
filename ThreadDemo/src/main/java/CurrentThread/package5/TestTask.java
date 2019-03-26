package CurrentThread.package5;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/6 9:18
 */
public class TestTask {
    private final ExecutorService exec;
    private final BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>();
    //启动门，当线程就绪时调用countDown
    private final CountDownLatch startLock = new CountDownLatch(1);
    //结束门
    private final CountDownLatch endLock;
    //线程数量
    private final int nThread;

    private AtomicInteger count = new AtomicInteger();

    public TestTask(int nThread) {
//        初始化线程数量
        this.nThread = nThread;
//        创建线程池，线程池共有nThread个线程
        exec = Executors.newFixedThreadPool(nThread);
//        设置结束门计数器，当一个线程结束是调用countDown
        endLock = new CountDownLatch(nThread);
    }

    private class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
//            线程启动后调用await，当前线程阻塞，只有启动门计数器为0时当前线程才会往下执行
            startLock.await();
            try {
                for(int i= 0;i<100;i++){
                    count.incrementAndGet();
                }
                return  count.get();
            } finally {
                endLock.countDown();
            }
        }
    }

    public void init() throws InterruptedException, ExecutionException {
        for(int i=0; i<nThread;i++){
//            将任务提交给线程池
            Future<Integer> future = exec.submit(new Task());
//            将Future实例添加到队列
            queue.add(future);
        }
//        所有任务添加完毕，启动门计数器减1，如果这时计数器为0，所有添加的任务开始执行
        startLock.countDown();
//       主线程阻塞，直到所有线程执行完毕
        endLock.await();
        for(Future<Integer> future:queue){
            System.out.println(future.get());
        }
        System.out.println(count);
        exec.shutdown();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestTask task = new TestTask(8);
        task.init();
    }
}
