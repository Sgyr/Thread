package Monitor;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/4 9:54
 */
public class ThisMonitorChange {
    public  void method1(){
        synchronized (this) {
            System.out.println(currentThread().getName() + " enter to method1");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void method2(){
        synchronized (this){
            System.out.println(currentThread().getName()+" enter to method2");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1,"T1").start();
        new Thread(thisMonitor::method2,"T2").start();
    }
}
