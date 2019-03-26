package CurrentThread.package5;

import java.util.concurrent.Semaphore;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/8 10:12
 */
public class Pool {
    private static int MAX_AVALLABLE = 100;

    private final Semaphore available = new Semaphore(MAX_AVALLABLE,true);


}
