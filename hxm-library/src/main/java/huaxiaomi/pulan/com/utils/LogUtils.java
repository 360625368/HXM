package huaxiaomi.pulan.com.utils;

import android.util.Log;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 18:26
 */
public class LogUtils {

    private static boolean enable;
    private static final String TAG = "HXM_LOG";

    public static void setEnable(boolean enable){
        LogUtils.enable = enable;
    }

    public static void log(String log){
        if(enable){
            Log.d(TAG,log);
        }
    }
}
