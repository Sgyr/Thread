package chapter6;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/22 15:16
 */
public class ThreadGroupEnumerateThreadGroups {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup myGroup1 = new ThreadGroup("MyGroup1");
        ThreadGroup myGroup2 = new ThreadGroup(myGroup1,"MyGroup2");

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup[] list = new ThreadGroup[mainGroup.activeCount()];

        int recerseSize = mainGroup.enumerate(list);
        System.out.println(recerseSize);

        recerseSize = mainGroup.enumerate(list,false);
        System.out.println(recerseSize);

    }
}
