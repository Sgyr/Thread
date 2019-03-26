package chapter5;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/16 17:26
 */
public class SynchronizedDefect {

    public synchronized void syncMehod(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        SynchronizedDefect defect = new SynchronizedDefect();
//        Thread t1 = new Thread(defect::syncMehod,"T1");
//        Thread t2 = new Thread(defect::syncMehod,"T2");
//
//
//
//        try {
//            t1.start();
//            TimeUnit.MILLISECONDS.sleep(2);
//            t2.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        try {
            SynchronizedDefect defect = new SynchronizedDefect();
            Thread t1 = new Thread(defect::syncMehod,"T1");
            t1.start();
//        make sure the t1 started
            TimeUnit.MILLISECONDS.sleep(2);
            Thread t2 = new Thread(defect::syncMehod,"T2");
            t2.start();
//        make sure the t2 started
            TimeUnit.MILLISECONDS.sleep(2);
            t2.interrupt();
            System.out.println(t2.isInterrupted());
            System.out.println(t2.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
