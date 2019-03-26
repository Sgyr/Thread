package CurrentThread.package5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:sgyt
 * @Description:熟悉reset()的用法,barrier回归初始化状态
 * @Date:2019/3/11 9:17
 */
public class CycliBarrierTest02 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);
        System.out.println("如果是一个初始的cycliBarrier,则reset()之后，什么也不会发生");
        barrier.reset();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        如果是一个已经打开一次的cycliBarrier,则reset()之后什么也不会发生
        ExecutorService executorService = Executors.newCachedThreadPool();

//        等待两次
        for(int i=0;i<2;i++){
//              线程开始
            executorService.submit(()->{
                try {
                    barrier.await();
                    System.out.println("屏障回归");
                    System.out.println("222屏障已经打开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("222被中断");
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                    System.out.println("222被重置");
                }
            });

        }

        System.out.println("reset1  start....................");
        barrier.reset();
        System.out.println("reset1  end......................");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//       如果是一个 有线程正在等待的线程，则reset()方法会使正在等待的线程抛出异常
        executorService.submit(()->{
           executorService.submit(()->{
               try {
                   barrier.await();
                   System.out.println("333屏障已经打开");
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   System.out.println("333被中断");
               } catch (BrokenBarrierException e) {
//                   e.printStackTrace();
                   System.out.println("在等待过程中，执行reset()方法，等待的线程抛出BrokenBarrierException异常，并不在等待");
               }
           });
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("reset2  start....................");
        barrier.reset();
        System.out.println("reset2  end......................");

        executorService.shutdown();


    }
}
