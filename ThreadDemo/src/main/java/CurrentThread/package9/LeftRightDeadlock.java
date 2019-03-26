package CurrentThread.package9;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:sgyt
 * @Description:锁顺序死锁
 * @Date:2019/3/26 11:03
 */
public class LeftRightDeadlock {
    private final Object left = new Object();

    private final Object right = new Object();

    public void leftRight(){
        synchronized (left){
            synchronized (right){
                System.out.println("left+right");
            }
        }
    }

    public void rightLeft(){
        synchronized (right){
            synchronized (left){
                System.out.println("right+left");
            }
        }
    }

}
