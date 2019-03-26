package chapter8;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/31 10:51
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String s) {
        super(s);
    }
}
