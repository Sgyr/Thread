package chapter5;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {
    /**
     * 永远阻塞，除非获取到了锁，这一点和synchronized非常类似，但是该方法可以被打断，中断报InterruptedException异常
     *
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 方法除了可以被中断以外，增加了对应超时功能
     * @param mills
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 进行锁的释放
     */
    void unclock();

    /**
     * 用于获取当前有哪些线程被阻塞
     * @return
     */
    List<Thread> getBlockedThreads();
}
