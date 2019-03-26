package CurrentThread.package6;

import java.text.DateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/14 13:41
 */
public class TimeTaskDemo01 {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        try {
            SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timer.schedule(new ThrowTask(),1);

        try {
            SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束");
    }


    static   class ThrowTask extends TimerTask{

        @Override
        public void run() {
            throw new RuntimeException();
        }
    }

}
