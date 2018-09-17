package huaxiaomi.pulan.com.hxm;

import android.app.Application;

import huaxiaomi.pulan.com.HXMManager;

/**
 * Description:
 * -
 * Compang:华通嘉联
 * Author：chasen
 * Date： 2018/9/13 11:13
 */
public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HXMManager.init(this);
        HXMManager.setLogEnable(true);
        HXMManager.initDaoConfig("test");
    }
}
