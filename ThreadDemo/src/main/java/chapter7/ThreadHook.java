package chapter7;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/28 9:16
 */
public class ThreadHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println("The hook Thread 1 will running.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 1 will exit.");
            }
        });

//        钩子线程可注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println("The hook thread 2 is running.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 2 will exit.");
            }

        });


        System.out.println("The program will is stopping.");

    }
}
