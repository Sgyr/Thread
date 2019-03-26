package CurrentThread.package5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author:sgyt
 * @Description:生产者消费者的模式
 * @Date:2019/3/4 10:14
 */
public class ProCustModel {
    public static void main(String[] args) {
//        直接定义为容器只能为10个
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        Producer producer1 = new Producer(queue);

        Producer producer2 = new Producer(queue);

        Producer producer3 = new Producer(queue);

        Consumer customer = new Consumer(queue);

//        借助Exectors创建线程池
        ExecutorService service = Executors.newCachedThreadPool();

//        启动线程
        service.execute(producer1);

        service.execute(producer2);

        service.execute(producer3);

        service.execute(customer);

//        执行10s
        try {
            Thread.sleep(10*1000);
            producer1.stop();
            producer2.stop();
            producer3.stop();

            Thread.sleep(2000);

//            退出Executor
            service.shutdown();
            System.out.println("结束所有的线程。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
