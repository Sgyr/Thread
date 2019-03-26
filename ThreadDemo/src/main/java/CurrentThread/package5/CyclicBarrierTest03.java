package CurrentThread.package5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author:sgyt
 * @Description:练习CyclicBarrier(int parties, Runnable barrierAction)的用法
 * @Date:2019/3/11 9:54
 */
public class CyclicBarrierTest03 {
    
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2,()->{
            System.out.println("屏障放开，先运行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("屏障的事情完成");
        });


        for(int i=0;i<2;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"等待屏障开放");
                try {
                    barrier.await();
                    System.out.println("握手");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"屏障开开放，开始干活");
            }).start();
        }
    }
}
