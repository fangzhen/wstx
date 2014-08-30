package info.fzhen.wstx.util;

import java.util.UUID;

public class CommonUtils {

    /**
     * Generate a unique id
     * @return String representation of the unique id
     */
    public static String genPrivateId() {
        return UUID.randomUUID().toString();
    }
}
