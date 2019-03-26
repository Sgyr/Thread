package CurrentThread.package8;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/23 10:19
 */
public class ThreadLocalTest {
    ThreadLocal<Long> longLocal = new ThreadLocal<>();

    ThreadLocal<String> stringLocal = new ThreadLocal<>();

    public void set(){
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong(){
        return longLocal.get();
    }

    public String getString(){
        return stringLocal.get();
    }
    
    public static void main(String[] args) {
        final ThreadLocalTest test = new ThreadLocalTest();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        System.out.println(".......................................");

        Thread tr = new Thread(){
            @Override
            public void run(){
//                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        };

        tr.start();
        try {
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.getLong());
        System.out.println(test.getString());

    }
}
