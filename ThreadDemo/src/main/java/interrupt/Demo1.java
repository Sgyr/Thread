package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/15 15:14
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
       Thread thread = new Thread(() ->
       {
           try {
               TimeUnit.MINUTES.sleep(1);
           } catch (InterruptedException e) {
               System.out.println("oh,i am be interrupted.");
           }
       });

       thread.start();

       TimeUnit.MICROSECONDS.sleep(2);
       thread.interrupt();
    }
}
