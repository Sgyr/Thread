package CurrentThread.package6;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/15 8:48
 */
public class FutureRenderer {
    public static final ExecutorService executor = Executors.newFixedThreadPool(5);

    static void renderPage(CharSequence source){
        final List<String> list = Arrays.asList("s","c","a","k");
        Callable<List<String>> task = new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                List<String> result = new ArrayList<>();
                for(String s:list){
                    SECONDS.sleep(3);
                    result.add(s);
                }
                System.out.println("子线程任务结束");
                return result;
            }
        };

        FutureTask futureTask = new FutureTask(task);
        Future<List<String>> future = executor.submit(task);

        System.out.println("我在主线程");

        try {
            List<String> result = future.get();
            for(String k:result){
                System.out.println("第二"+k);
            }
            System.out.println("主线程结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        CharSequence char1 = "sss";
        renderPage(char1);
        executor.shutdown();
    }
}
