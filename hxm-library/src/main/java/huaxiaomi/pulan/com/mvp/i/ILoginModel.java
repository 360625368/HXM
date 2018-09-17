package huaxiaomi.pulan.com.mvp.i;

import huaxiaomi.pulan.com.http.NetCallBack;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:26
 */
public interface ILoginModel extends IBaseModel {

    void requestLogin(String username, String psw, String imei, NetCallBack callBack);
}
