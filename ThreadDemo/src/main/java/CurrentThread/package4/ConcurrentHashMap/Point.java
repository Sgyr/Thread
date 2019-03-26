package CurrentThread.package4.ConcurrentHashMap;

import java.io.Serializable;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/2/25 14:37
 */
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;

    public final int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
