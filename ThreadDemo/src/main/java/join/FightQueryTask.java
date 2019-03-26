package join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/26 10:53
 */
public class FightQueryTask extends Thread implements FightQuery {

//    开始的地方
    private  String origin;

//    目的地
    private  String destination;

    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airLine,String origin,String destination){
        super("["+airLine+"]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n",getName(),origin,destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName()+"-"+randomVal);
            System.out.printf("The Fight:%s list query successful\n",getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}


