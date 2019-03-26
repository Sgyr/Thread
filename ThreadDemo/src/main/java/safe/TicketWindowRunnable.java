package safe;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2018/12/30 13:01
 */
public class TicketWindowRunnable implements Runnable{

    private int index = 1;

    private final static int Max = 500;


    @Override
    public void run() {
        while(index <= Max){
            System.out.println(Thread.currentThread()+"的号码"+(index++));
        }
    }

    public static void main(String[] args) {
        final TicketWindowRunnable task = new TicketWindowRunnable();

        Thread windowThread1 = new Thread(task,"一号窗口");

        Thread windowThread2 = new Thread(task,"二号窗口");

        Thread windowThread3 = new Thread(task,"三号窗口");

        Thread windowThread4 = new Thread(task,"四号窗口");

        windowThread1.start();

        windowThread2.start();

        windowThread3.start();


        windowThread4.start();
    }
}
