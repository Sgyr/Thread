package CurrentThread.package7;

import java.io.File;
import java.util.Currency;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/21 9:35
 */
public class IndexingService {
    private static final int CAPACITY = 1000;

    private static final File POISON = new File("");

    private final IndexerThread consumer = new IndexerThread();

    private final CrawlerThread producer = new CrawlerThread();

    private final BlockingQueue<File> queue;

    private final File root;

    public IndexingService(File root) {
        this.queue = new LinkedBlockingQueue<File>(CAPACITY);
        this.root = root;
    }

    private boolean alredyIndexed(File file){
        return false;
    }


    //消费者
    class IndexerThread extends Thread{
        @Override
        public void run(){
            while(true){
                try {
                    File file = queue.take();
                    if(file == POISON){
                        System.out.println("遇到毒丸，终止");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //生产者
    class CrawlerThread extends Thread{
        @Override
        public void run(){
            try {
                crawl(root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles();
        if(entries != null){
            for(File entry:entries){
                if(entry.isDirectory()){
                    crawl(entry);
                }else if(!alredyIndexed(entry)){
                    System.out.println("放入生产者队列文件："+entry.getName()+"来自线程"+Thread.currentThread().getName());
                    queue.put(entry);
                }
            }
        }
    }

    public void start(){
        producer.start();
        consumer.start();
    }

    public void stop(){
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

}
