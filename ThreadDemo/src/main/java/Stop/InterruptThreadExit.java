package Stop;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:线程正常关闭，结束生命周期（通过捕获中断信息）
 * @Date:2018/12/28 14:39
 */
public class InterruptThreadExit {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run(){
                System.out.println("I will start work");
                while(isInterrupted()){
                    System.out.println("========i still work========");
                }
                System.out.println("I will be exiting");
            }
        };

        t.start();
        try {
            TimeUnit.MINUTES.sleep(1);
            System.out.println("System will be shutdown");
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
