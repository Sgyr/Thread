package chapter5;


import org.junit.Test;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/12 9:15
 */
public class error1 {
    @Test
    public void testWait(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testNotify(){
        this.notify();
    }
}
/**
 * 说明wait,notify的方法只能在同步方法中使用
 */
