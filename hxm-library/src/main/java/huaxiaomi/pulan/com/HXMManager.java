package huaxiaomi.pulan.com;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import org.xutils.DbManager;
import org.xutils.x;

import huaxiaomi.pulan.com.utils.LogUtils;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 11:12
 */
public class HXMManager {


    /**
     * 初始化
     * @param application
     */
    public static void init(Application application){
        x.Ext.init(application);
        Utils.init(application);
    }

    /**
     * 初始化数据库配置
     * @param dbName    数据库名字，不需要后缀名".db"
     */
    public static void initDaoConfig(String dbName){
        HXMDaoManager.initDaoConfig(dbName);
    }

    /**
     * 打开or关闭HXM日志输出
     * @param enable
     */
    public static void setLogEnable(boolean enable){
        LogUtils.setEnable(enable);
    }
}
