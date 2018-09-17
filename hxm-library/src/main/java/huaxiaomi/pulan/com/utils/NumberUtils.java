package huaxiaomi.pulan.com.utils;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 17:56
 */
public class NumberUtils {

    public static int toInt(String str,int defaultValue){
        int result = defaultValue;
        try {
            result = Integer.parseInt(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static float toFloat(String str,float defaultValue){
        float result = defaultValue;
        try {
            result = Float.parseFloat(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
