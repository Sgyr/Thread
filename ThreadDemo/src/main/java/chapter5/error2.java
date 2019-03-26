package chapter5;

import org.junit.Test;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/12 12:59
 */
public class error2 {
    private final Object MUTEX = new Object();

    @Test
    public synchronized void testWait(){
        try {
            MUTEX.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void testNofity(){
        MUTEX.notify();
    }

}
/**
 * 同步代码的monitor必须与执行wait notify方法的对象一致
 */
