package chapter8;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/31 11:15
 */
public class InternalTask implements Runnable{

    private   RunnableQueue runnableQueue;

    private volatile  boolean running = true;

    public  InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
//        如果当前任务为running并且没有被中断，测其将不断从queue中获取runnable,然后执行run方法
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }
    //停止当前任务，主要会在线程池的shutdown方法中使用
    public void stop(){
        this.running = false;
    }
}
