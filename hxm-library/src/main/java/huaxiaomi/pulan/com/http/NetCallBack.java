package huaxiaomi.pulan.com.http;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:11
 */
public interface NetCallBack<T> {

    void onSuccess(T result);

    void onCancel();

    void onError(Throwable throwable);

    void onFinish();
}
