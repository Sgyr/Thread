package safe;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/30 14:24
 */
public class Mutex {
    private final  static Object MUTEX = new Object();

    public static void accessResource(){
        synchronized (MUTEX){
                try {
                    TimeUnit.MINUTES.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        final Mutex mutex = new Mutex();
        for(int i=0;i<5;i++){
            new Thread(Mutex::accessResource).start();
        }

    }
}
