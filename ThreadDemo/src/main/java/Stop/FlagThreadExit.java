package Stop;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/28 16:07
 */
public class FlagThreadExit {
    static class MyTask extends Thread{
        private volatile boolean close = false;
    }
}
