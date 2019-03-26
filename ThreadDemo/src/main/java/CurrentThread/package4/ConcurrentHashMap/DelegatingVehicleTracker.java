package CurrentThread.package4.ConcurrentHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author:sgyt
 * @Description:线程安全的类
 * @Date:2019/2/25 14:36
 */
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String,Point> locations;

    private final Map<String,Point> unmodifiableMap;

    public DelegatingVehicleTracker(ConcurrentMap<String, Point> locations, Map<String, Point> unmodifiableMap) {
        this.locations = locations;
        this.unmodifiableMap = unmodifiableMap;
    }

    public ConcurrentMap<String, Point> getLocations() {
        return locations;
    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocation(String id,int x,int y){
        if(locations.replace(id,new Point(x,y)) == null){
            throw new IllegalArgumentException("invalid vehicle name :"+id);
        }
    }
}
