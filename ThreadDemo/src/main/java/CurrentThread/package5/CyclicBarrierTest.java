package CurrentThread.package5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author:sgyt
 * @Description:了解CyclicBarrier(parties)/getParties()/await()/getNumberWaiting()的基本用法。
 * @Date:2019/3/11 8:23
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
//        构造：初始化-开启屏障的方数
        CyclicBarrier barrier0 = new CyclicBarrier(2);
//        获取屏障的方数‘
        System.out.println("barrier.getParties()获取的方数："+barrier0.getParties());
//        获取barrier.getNumberWaiting()获取正在等待的线程数
        System.out.println("barrier.getNumberWaiting()获取正在等待的线程数："+barrier0.getNumberWaiting());
        new Thread(()->{
            System.out.println("添加一个线程------"+Thread.currentThread().getName());
            try {
                barrier0.await();
                System.out.println(Thread.currentThread().getName()+"is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"is terminated.");
        }).start();

        Thread.sleep(10);

        System.out.println("通过barrier.getNumberWaiting()获取正在等待的线程数：添加第一个等待的线程--"+barrier0.getNumberWaiting());

        Thread.sleep(10);

        new Thread(()->{
            System.out.println("添加第二个等待线程---"+Thread.currentThread().getName());
            try {
                barrier0.await();
                System.out.println(Thread.currentThread().getName()+"is running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"is terminated.");
        }).start();

        Thread.sleep(10);

        System.out.println("通过barrier.getNumberWaititing()获取正在等待的线程数：打开屏障之后--"+barrier0.getNumberWaiting());

        Thread.sleep(10);

        new Thread(()->{
            System.out.println("屏障打开后，再有线程加入等待"+Thread.currentThread().getName());
            try {
//                BrokenBarrierException
                barrier0.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"is terminated.");
        }).start();

        Thread.sleep(10);

        System.out.println("barrier.getNumberWaiting()获取正在等待的线程数，打开屏障之后---"+barrier0.getNumberWaiting());

    }
}
