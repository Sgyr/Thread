package chapter8;

@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
