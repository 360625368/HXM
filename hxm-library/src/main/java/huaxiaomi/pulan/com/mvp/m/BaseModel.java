package huaxiaomi.pulan.com.mvp.m;

import android.content.Context;

import org.xutils.x;

import huaxiaomi.pulan.com.http.HttpClient;
import huaxiaomi.pulan.com.http.IHttpClient;
import huaxiaomi.pulan.com.mvp.i.IBaseModel;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:10
 */
public class BaseModel implements IBaseModel {


    @Override
    public IHttpClient getHttpClient() {
        return HttpClient.newInstance();
    }

    @Override
    public Context getContext() {
        return x.app();
    }
}
