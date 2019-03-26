package chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/19 15:24
 */
public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod(){
        try {
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread()+"get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unclock();
        }

    }

    public void syncMethodTimeouttable(){
        try {
            lock.lock(100);
            System.out.println(currentThread()+"get the lock.");
            int randomInt = current().nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            lock.unclock();
        }
    }

//    public static void main(String[] args){
//        BooleanLockTest blt = new BooleanLockTest();
//        IntStream.range(0,10).mapToObj(i -> new Thread(blt::syncMethod)).forEach(Thread::start);
//    }

//    public static void main(String[] args) {
//        try {
//            BooleanLockTest blt = new BooleanLockTest();
//            new Thread(blt::syncMethod,"T1").start();
//            TimeUnit.MILLISECONDS.sleep(2);
//            Thread t2 = new Thread(blt::syncMethod,"T2");
//            t2.start();
//            TimeUnit.MILLISECONDS.sleep(10);
//            t2.interrupt();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        BooleanLockTest blt = new BooleanLockTest();
        try {
            new Thread(blt::syncMethod,"T1").start();
            TimeUnit.MILLISECONDS.sleep(2);
            Thread t2 = new Thread(blt::syncMethodTimeouttable,"T2");
            t2.start();
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
