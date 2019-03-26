package CurrentThread.package8;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/25 14:15
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(){
            @Override
            public void run(){
                System.out.println("子线程"+ Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完成");
                latch.countDown();
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                System.out.println("子线程"+ Thread.currentThread().getName()+"正在执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完成");
                latch.countDown();
            }
        }.start();


        new Thread(){
            @Override
            public void run(){
                System.out.println("等待2个子线程执行完毕...");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2个子线程已经执行完毕");
            }
        }.start();

        System.out.println("主线程完成");
    }

}
