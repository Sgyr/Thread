package CurrentThread.package5;

import java.util.concurrent.Semaphore;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/8 10:37
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i=0;i<5;i++){
            MyThread myThread = new MyThread(""+i,semaphore);
            myThread.start();
        }
    }

    static class MyThread extends Thread{
        String name;
        Semaphore semaphore;
        public MyThread(String name,Semaphore semaphore){
            this.name = name;
            this.semaphore = semaphore;
        }

        @Override
        public void run(){
            try {
//              线程中获取许可
                semaphore.acquire();
                System.out.println(name+"获取信号量，开始工作。。。");
                Thread.sleep(3000);
//                返回正在等待获取许可证的线程数。
                System.out.println(name+"工作结束，正在等待获取许可证的线程数："+semaphore.getQueueLength());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
//                释放许可证，释放回信号量
                semaphore.release();
            }
        }
    }
}
