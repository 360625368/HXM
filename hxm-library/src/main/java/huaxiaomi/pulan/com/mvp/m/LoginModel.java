package huaxiaomi.pulan.com.mvp.m;

import java.util.HashMap;

import huaxiaomi.pulan.com.config.HttpConfig;
import huaxiaomi.pulan.com.http.NetCallBack;
import huaxiaomi.pulan.com.mvp.i.ILoginModel;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:27
 */
public class LoginModel extends BaseModel implements ILoginModel {

    private static final String LOGIN_URL = HttpConfig.BASE_URL + "message/app/isRegisterIMEI";

    @Override
    public void requestLogin(String username, String psw,String imei, NetCallBack callBack) {
        HashMap<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",psw);
        params.put("imei",imei);
        getHttpClient().doJsonPost(LOGIN_URL, params,callBack);
    }
}
