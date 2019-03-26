package CurrentThread.package7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/20 8:43
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run(){
        BigInteger p = BigInteger.ONE;
        while(!cancelled){
            try {
                queue.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancal(){
        this.cancelled = false;
    }







}

