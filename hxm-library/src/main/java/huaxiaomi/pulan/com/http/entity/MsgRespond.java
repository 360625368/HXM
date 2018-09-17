package huaxiaomi.pulan.com.http.entity;

/**
 * Description:请求相应基类
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 17:28
 */
public class MsgRespond<T> {

    private int status;
    private String type;
    private String error;
    private T resp;

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getError() {
        return error;
    }

    public T getResp() {
        return resp;
    }
}
