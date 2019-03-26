package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/19 10:21
 */
public class Demo4 {
    public static void main(String[] args) {
//        判断当前线程是否被中断
        System.out.println("Main thread is interrupted?"+Thread.interrupted());

//        中断当前线程
        Thread.currentThread().interrupt();

//        判断当前线程是否已经被中断
        System.out.println("Main thread is interrutped?"+Thread.currentThread().isInterrupted());
//        System.out.println("Main thread is interrutped?"+Thread.interrupted());
//        System.out.println("Main thread is interrutped?"+Thread.currentThread().isInterrupted());
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupted still");
        }
    }
//    这段程序说明，如果表示了线程isInterrupt的标识，该线程如果再次进去线程阻塞是会直接抛出异常
}
