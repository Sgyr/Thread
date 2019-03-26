package CurrentThread.package5;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:sgyt
 * @Description:运用场景
 * 模拟多线程分组计算
 * 有一个大小为50000的随机数组，用5个线程分别计算10000个元素的和
 * 然后在将计算结果进行合并，得出最后的结果。
 * 重点分析：
 *
 * 用5个线程分别计算：定义一个大小为5的线程池。
 * 计算结果进行合并：定义一个屏障线程，将上面5个线程计算的子结果信息合并。
 * @Date:2019/3/11 10:00
 */
public class CyclicBarrierTest04 {
    public static void main(String[] args) {
        Random random = new Random();
//        数组大小
        int size = 50000;
//        定义数组
        int [] numbers = new int[size];
//        随机初始化数组
        for(int i=0;i<size;i++){
            numbers[i] = 100+random.nextInt(1000);
        }

        long start = System.currentTimeMillis();
//        单线程计算结果
        Long sum = 0L;
        for(int i=0;i<size;i++){
            sum += numbers[i];
        }
        long end=  System.currentTimeMillis();


        System.out.println("单线程计算结果:"+sum);
        System.out.println("计算时间："+(end-start));


//        多线程计算结果
        ExecutorService executorService = Executors.newFixedThreadPool(5);

//        定义五个Future区保存子数组的计算结果
        final int[] results= new int[5];
        long time = 0L;
//        定义一个循环屏障,在屏障线程中进行计算结果合并
        CyclicBarrier barrier = new CyclicBarrier(5,()->{
            int sums=0;
            for(int i=0;i<5;i++){
                sums += results[i];
            }
            System.out.println("多线程计算："+sums+"计算时间：");
        });
//      子数组长度
        int length = 10000;

        for(int i=0;i<5;i++){
//            定义子数组
            int[] subNumbers = Arrays.copyOfRange(numbers,(i*length),(i+1)*length);
//            放最后的计算结果
            int finali = i;
            executorService.submit(()->{
               for(int j=0;j<subNumbers.length;j++){
                    results[finali]+=subNumbers[j];
               }
//               等待其他子线程一起
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();

    }

}
