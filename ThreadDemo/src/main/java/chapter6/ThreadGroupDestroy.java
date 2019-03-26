package chapter6;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/24 14:33
 */
public class ThreadGroupDestroy {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("group.isDestoryed = "+group.isDestroyed());
        mainGroup.list();

        group.destroy();

        System.out.println("group.isDestoryed ="+group.isDestroyed());
        mainGroup.list();

    }
}
