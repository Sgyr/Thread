package Demo;

import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/2 8:54
 */
public class Return {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        String k = Run();
        System.out.println("主线程结束");
    }

    public static String Run(){
        Thread thread = new Thread(){
          @Override
          public void run(){
              try {
                  System.out.println("子线程开始睡眠");
                  TimeUnit.MINUTES.sleep(1);
                  System.out.println("子线程结束睡眠");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };
        thread.start();
        return "OK";
    }
}
