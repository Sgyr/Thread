package chapter5;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/17 14:13
 */
public class EventQueueChange {
    private  int max;

    static class Event{

    }

    private final LinkedList<Event> eventQueue = new LinkedList<Event>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueueChange(){
//        this(DEFAULT_MAX_EVENT);
        this.max = DEFAULT_MAX_EVENT;
    }

    public EventQueueChange(int max){
        this.max = max;
    }


    public void offer(Event event){
//        锁的是数据
        synchronized (eventQueue){
            while(eventQueue.size() >= max){
                try {
                    console("the queue is full");
//                    数据锁住
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is sumbitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            while(eventQueue.isEmpty()){
                console("the queue is empty.");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console("the event"+event+"is handled.");
            return event;
        }
    }



    private void console(String message) {
        System.out.printf("%s:%s\n",currentThread(),message);
    }


}
/**
*@功能描述
*@description:生产消费模式的改进
*/