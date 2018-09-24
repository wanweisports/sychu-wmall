package Reader;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

/**
 * Created by 陈小松 on 2018/9/22.
 */
public interface ReaderMethod extends StdCallLibrary {

    ReaderMethod reader = (ReaderMethod) Native.loadLibrary("RePackLib", ReaderMethod.class);

    void Reset(String a);

    public static void main(String[] args) {
        System.setProperty("jna.debug_load", "true");
        //ReaderMethod reader = new ReaderMethod();
        byte[] b = new  byte[1];
        reader.Reset("");
    }

}
