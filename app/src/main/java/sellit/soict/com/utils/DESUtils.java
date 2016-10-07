package sellit.soict.com.utils;

import android.content.Context;
import android.util.Base64;

/**
 * Created by Dang Hien on 29/12/2015.
 */
public class DESUtils {

    public static String encrypt( String data, String key) {
        byte dataArray[] = data.getBytes();
        byte keyArray[] = key.getBytes();
        byte cipher[] = DES.encrypt(dataArray, keyArray);
        return new String(Base64.encode(cipher, 0));
    }

    public static String decrypt(String data, String key) {
        byte keyArray[] = key.getBytes();
        byte dataArray[] = Base64.decode(data, 0);
        return new String(DES.decrypt(dataArray, keyArray));
    }
}
