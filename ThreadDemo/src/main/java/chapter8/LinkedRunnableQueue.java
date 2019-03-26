package chapter8;

import java.util.LinkedList;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/31 14:39
 */
public class LinkedRunnableQueue implements RunnableQueue{
//    任务队列的最大容量，在构造时传入
    private int limit;

//    若任务队列中的任务已经满了,则需要执行拒绝策略
    private DenyPolicy denyPolicy;

//    存放任务队列
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList){
            if(runnableList.size()>=limit){
//               无法容纳新的任务时执行拒绝策略
                denyPolicy.reject(runnable,threadPool);
            }else {
//                将任务加到队尾，并且唤醒阻塞中的线程
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() {
        synchronized (runnableList){
            while(runnableList.isEmpty()){
                try{
                    runnableList.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableList){
//            返回当前任务队列中的任务数
            return runnableList.size();
        }
    }
}

