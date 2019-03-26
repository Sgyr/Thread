package MD5;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/5 12:03
 */
public class Demo01 {
    public static void main(String[] args) {
        File file = new File("F:\\1546657532145.xml");
        if(file.isFile()){
            MessageDigest digest = null;
            byte[] buffer = new  byte[1024];
            try {
                digest = MessageDigest.getInstance("MD5");
                FileInputStream fileInputStream = new FileInputStream(file);
                int len = fileInputStream.read(buffer);
                while (len != -1){
                    digest.update(buffer,0,len);
                    len = fileInputStream.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            BigInteger bigit = new BigInteger(1,digest.digest());
            String md5 = bigit.toString(16);
            System.out.println(md5);

        }
    }
}
