package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/19 9:05
 */
public class Demo3 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run(){
                while(true){

                }
            }
        };
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
            System.out.printf("Thread is interrupted? %s\n",thread.isInterrupted());
            thread.interrupt();
            System.out.printf("Thread is interrupted? %s\n",thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
