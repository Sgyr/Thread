package chapter5;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/9 16:49
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->
        {
           for(;;)
           {
               eventQueue.offer(new EventQueue.Event());
           }
        },"Producer").start();

        new Thread(()->{
            for(;;){
                eventQueue.take();
                try {
                    TimeUnit.MINUTES.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();

        System.out.println("Main 线程已经结束");

    }
}
