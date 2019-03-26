package chapter5;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/19 13:27
 */
public class BooleanLock implements Lock {
    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while(locked){

                final Thread tempThread = currentThread();
                try {
                    if(!blockedList.contains(tempThread)){
                        blockedList.add(currentThread());
                        this.wait();
                    }
                } catch (InterruptedException e) {
//                  如果当前线程在wait时被中断，册从blockedList中将其删除，避免内存泄漏
                    blockedList.remove(tempThread);
                    throw e;
                }
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills <= 0){
                this.lock();
            }else{
                long remainingMills = mills;
//                预计结束时间
                long endMills = System.currentTimeMillis() + remainingMills;
                while(locked){
//                    超过预计时间时
                    if(remainingMills<=0) {
                        throw new TimeoutException("can not get the lock during" + mills);
                    }
                    if(!blockedList.contains(currentThread())) {
                        blockedList.add(currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unclock() {
        synchronized (this){
            if(currentThread == currentThread()){
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
