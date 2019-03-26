package CurrentThread.package3.finalFunction;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Author:sgyt
 * @Description:对数值及其因数分解结果进行缓存的不可变容器类（创建一个不可变的类来包含数据，而不可变的类都是用final域来定义）
 * @Date:2019/2/15 8:02
 */
public class OneValueCache {
    private final BigInteger lastNumber;

    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = lastFactors;
    }

    public BigInteger[] getFactors(BigInteger i){
        if(lastNumber == null || !lastNumber.equals(i)){
            return null;
        }else {
            return Arrays.copyOf(lastFactors,lastFactors.length);
        }
    }
}

