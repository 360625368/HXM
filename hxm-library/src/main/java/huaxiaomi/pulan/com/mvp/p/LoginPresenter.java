package huaxiaomi.pulan.com.mvp.p;

import com.blankj.utilcode.util.DeviceUtils;

import huaxiaomi.pulan.com.http.DefaultNetCallBack;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.ILoginModel;
import huaxiaomi.pulan.com.mvp.i.ILoginPresent;
import huaxiaomi.pulan.com.mvp.v.ILoginView;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:24
 */
public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresent {

    private ILoginModel loginModel;

    public LoginPresenter() {
        loginModel = MvpFactory.buildeM(ILoginModel.class);
    }

    @Override
    public void requestLogin(String username, String psw) {
        getProxyView().showProgressDialog("请稍后","正在登录...");
        loginModel.requestLogin(username, psw, DeviceUtils.getAndroidID(), new DefaultNetCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                getProxyView().loginResult(result);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onFinish() {
                getProxyView().dismissProgressDialog();
            }
        });
    }
}
