package CurrentThread.package4;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/2/26 9:22
 */
public class SafePoint {
    private int x;

    private int y;

    public SafePoint(int[] a) {
        this(a[0],a[1]);
    }

    public SafePoint(SafePoint p){
        this (p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get(){
        return new int[]{x,y};
    }

    public synchronized void set(int x,int y){
        this.x = x;
        this.y = y;
    }
}
