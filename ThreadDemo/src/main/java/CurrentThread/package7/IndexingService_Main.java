package CurrentThread.package7;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/21 9:53
 */
public class IndexingService_Main {

    public static void main(String[] args) {
        File file = new File("F:\\临时文档");
        IndexingService c = new IndexingService(file);
        c.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.stop();


    }
}
