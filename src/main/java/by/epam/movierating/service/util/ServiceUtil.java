package by.epam.movierating.service.util;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * Created by Даша on 14.02.2017.
 */
public class ServiceUtil {
    public static String encodePassword(byte[] password){
        return DigestUtils.md5Hex(password);
    }
}
