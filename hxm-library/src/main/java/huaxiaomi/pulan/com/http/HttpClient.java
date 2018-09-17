package huaxiaomi.pulan.com.http;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import huaxiaomi.pulan.com.utils.LogUtils;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:13
 */
public class HttpClient implements IHttpClient {

    private Gson gson;
    private static HttpClient instance;

    private HttpClient(){
        gson = new Gson();

    }

    public static synchronized HttpClient newInstance(){
        if(instance == null){
            instance = new HttpClient();
        }
        return instance;
    }

    @Override
    public void doGet(String url, Map<String, String> params, NetCallBack callBack) {
        RequestParams request = new RequestParams(url);
        for(Map.Entry<String,String> entry : params.entrySet()){
            request.addQueryStringParameter(entry.getKey(),entry.getValue());
        }
        x.http().get(request,buildeCallback(callBack));
    }

    @Override
    public void doPost(String url, Map<String, String> params, NetCallBack callBack) {
        RequestParams request = new RequestParams(url);
        for(Map.Entry<String,String> entry : params.entrySet()){
            request.addBodyParameter(entry.getKey(),entry.getValue());
        }
        x.http().post(request,buildeCallback(callBack));
    }

    @Override
    public void doJsonPost(String url, Map<String, String> params, NetCallBack callBack) {
        RequestParams request = new RequestParams(url);
        request.setAsJsonContent(true);
        request.setBodyContent(gson.toJson(params));
        x.http().post(request,buildeCallback(callBack));
    }

    @Override
    public void doJsonPost(String url, String json, NetCallBack callBack) {
        RequestParams request = new RequestParams(url);
        request.setAsJsonContent(true);
        request.setBodyContent(json);
        x.http().post(request,buildeCallback(callBack));
    }

    @Override
    public void doJsonPost(String url, Object o, NetCallBack callBack) {
        RequestParams request = new RequestParams(url);
        request.setAsJsonContent(true);
        request.setBodyContent(gson.toJson(o));
        x.http().post(request,buildeCallback(callBack));
    }

    private Callback.CommonCallback buildeCallback(final NetCallBack callBack){
        return new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log(result);

                ParameterizedType parameterizedType = (ParameterizedType) callBack.getClass().getGenericSuperclass();
                Type type = parameterizedType.getActualTypeArguments()[0];
                if(type == String.class){
                    callBack.onSuccess(result);
                }else{
                    callBack.onSuccess(gson.fromJson(result,type));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callBack.onCancel();
            }

            @Override
            public void onFinished() {
                callBack.onFinish();
            }
        };
    }
}
