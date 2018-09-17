package huaxiaomi.pulan.com.mvp.i;

import android.content.Context;

import huaxiaomi.pulan.com.http.IHttpClient;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/5 16:32
 */
public interface IBaseModel {

    IHttpClient getHttpClient();

    Context getContext();
}
