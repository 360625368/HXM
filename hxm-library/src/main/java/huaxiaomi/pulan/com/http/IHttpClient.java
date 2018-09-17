package huaxiaomi.pulan.com.http;

import java.util.Map;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:10
 */
public interface IHttpClient {

    void doGet(String url, Map<String, String> params, NetCallBack callBack);

    void doPost(String url, Map<String, String> params, NetCallBack callBack);

    void doJsonPost(String url, Map<String, String> params, NetCallBack callBack);

    void doJsonPost(String url, String json, NetCallBack callBack);

    void doJsonPost(String url, Object o, NetCallBack callBack);
}
