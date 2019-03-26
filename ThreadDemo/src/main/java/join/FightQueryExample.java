package join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/27 18:05
 */
public class FightQueryExample {
//    合同的各大航空公司
    private static List<String> fightCompany = Arrays.asList("CSA","CEA","HNA");

    public static void main(String[] args) {
        List<String> results = search("SH","BJ");
        System.out.println("=============result===========");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original,String dest){
        final List<String> result = new ArrayList<>();

//        创建查询航班信息的线程列表
        List<FightQueryTask> tasks = fightCompany.stream().map(f->createSearchTask(f,original,dest)).collect(Collectors.toList());

//        分别启动这几个线程
        tasks.forEach(Thread::start);

//        分别调用每一个线程的join方法，阻塞当前线程
        tasks.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight,String original,String dest){
        return new FightQueryTask(fight,original,dest);
    }
}
