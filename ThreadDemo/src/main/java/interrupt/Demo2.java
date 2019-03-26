package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/18 21:16
 */
public class Demo2 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.printf("ExceptionThread is interrupted? %s\n",isInterrupted());
                    }
                }
            }
        };
//        守护线程
        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
            System.out.printf("Thread is interrupted? %s\n",thread.isInterrupted());
            thread.interrupt();
//            这里如果这句话去掉的话，会发生变化，因为主线程的检查子线程的状态会和实际的不同
            TimeUnit.MILLISECONDS.sleep(2);
            System.out.printf("Thread is interrupted? %s\n",thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
