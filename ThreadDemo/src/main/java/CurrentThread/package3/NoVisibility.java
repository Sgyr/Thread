package CurrentThread.package3;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/2/14 13:05
 */
public class NoVisibility {
    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {

            while(!ready){
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();

        number = 7;
        ready = true;
    }


}
