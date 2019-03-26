package chapter5;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/17 15:13
 */
public class EventClientChange {
    public static void main(String[] args) {
        final EventQueueChange eventQueueChange = new EventQueueChange();
        new Thread(()->
        {
            for(;;)
            {
                eventQueueChange.offer(new EventQueueChange.Event());
            }
        },"Producer").start();

        new Thread(()->{
            for(;;){
                eventQueueChange.take();
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
