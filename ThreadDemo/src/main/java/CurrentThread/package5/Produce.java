package CurrentThread.package5;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/2 14:02
 */
public class Produce {
    private String productName;

    private String id;

    public Produce(String productName, String id) {
        this.productName = productName;
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
