package CurrentThread.package3;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/2/14 15:41
 */
public class ThreeStooges {
    private  final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("ni");
        stooges.add("go");
        stooges.add("kk");
    }

    public  boolean isStooge(String name){
        return stooges.contains(name);
    }



}
