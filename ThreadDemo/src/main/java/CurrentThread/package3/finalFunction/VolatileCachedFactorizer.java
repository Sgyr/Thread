package CurrentThread.package3.finalFunction;


import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/2/15 11:04
 */
public class VolatileCachedFactorizer  implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null,null);


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
