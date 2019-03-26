package chapter8;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/31 15:41
 */

public class BasicThreadPool extends Thread implements ThreadPool {
//   初始化线程数量
    private int initSize;

//    线程池最大线程数量
    private int maxSize;

//    线程池核心线程数量
    private int coreSize;

//    当前活跃的线程数量
    private int activeCount;

//    创建线程所需的工厂
    private ThreadFactory threadFactory;

//    任务队列
    private RunnableQueue runnableQueue;

//    线程池是否已经被shutdown

    @Override
    public void execute(Runnable runnable) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public int getCoreSize() {
        return 0;
    }

    @Override
    public int getQueueSize() {
        return 0;
    }

    @Override
    public int getActiveCount() {
        return 0;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }
}
