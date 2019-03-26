package chapter7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/1/28 13:12
 */
public class PreventDuplicated {
    private final static String LOCK_PATH = "/home/wangwenjun/locks";

    private final static String LOCK_FILE = ".lock";

    private final static String PERMISSIONS = "rw-----------";

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The program received kill SIGNAL");
            getLockFile().toFile().delete();
        }));



    }

    private static void checkRunning() throws IOException
    {
        Path path = getLockFile();
        if(path.toFile().exists()){
            throw new RuntimeException("The p rogram already running.");
        }

        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));

    }

    public static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
