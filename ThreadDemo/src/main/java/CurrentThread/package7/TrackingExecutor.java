package CurrentThread.package7;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/21 10:39
 */
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;

    private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public List<Runnable> getCancelledTasks(){
        if(!exec.isTerminated()){
            throw new IllegalStateException();
        }
        return new ArrayList<Runnable>(tasksCancelledAtShutdown);
    }

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout,unit);
    }

    @Override
    public void execute(Runnable command) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    if(isShutdown()&&Thread.currentThread().isInterrupted()){
                        tasksCancelledAtShutdown.add(command);
                    }
                }
            }
        });
    }
}
