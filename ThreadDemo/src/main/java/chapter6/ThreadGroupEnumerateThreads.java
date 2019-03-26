package chapter6;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/22 13:14
 */
public class ThreadGroupEnumerateThreads {
    public static void main(String[] args) {
        ThreadGroup myGroup = new ThreadGroup("MyGroup");

        Thread thread = new Thread(myGroup,()->{
           while(true){
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"MyThread");

        thread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(2);
            ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

            Thread[] list = new Thread[mainGroup.activeCount()];
            int recurseSize = mainGroup.enumerate(list);
            System.out.println(recurseSize);

            recurseSize = mainGroup.enumerate(list,false);
            System.out.println(recurseSize);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
