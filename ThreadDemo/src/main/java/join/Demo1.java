package join;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/19 13:11
 */
public class Demo1 {
    public static void main(String[] args) {
        List<Thread> list = IntStream.range(1,3).mapToObj(Demo1::create).collect(toList());

//        启动线程
        list.forEach(item -> item.start());

//        执行线程的join方法
        list.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        main线程循环输出
        for(int i=0 ;i<10 ; i++){
            System.out.println(Thread.currentThread().getName()+"#"+i);
            shortSleep();
        }
    }


    private static Thread create(int seq){
        return new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"#"+i);
                shortSleep();
            }
        },String.valueOf(seq));
    }

    private static void shortSleep(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
