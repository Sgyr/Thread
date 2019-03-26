package chapter6;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/22 16:29
 */
public class ThreadGroupInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("TestGroup");
        new Thread(group,()->
        {
           while (true){
               try {
                   TimeUnit.MILLISECONDS.sleep(2);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
          // System.out.println("t1 will exit");
        },"t1").start();

        new Thread(group,()->
        {
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("t2 will exit.");
        },"t2");

        TimeUnit.MILLISECONDS.sleep(2);

        group.interrupt();
    }
}
