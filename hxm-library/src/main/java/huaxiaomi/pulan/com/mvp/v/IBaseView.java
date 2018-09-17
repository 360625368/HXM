package huaxiaomi.pulan.com.mvp.v;

import android.content.Context;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:13
 */
public interface IBaseView {

    void showToast(String message);

    void showLongToast(String message);

    void showProgressDialog(String title, String message);

    void showDefaultProgressDialog();

    void dismissProgressDialog();

    void initPresenter();

    void attachView();

    void dettachView();

    Context getContext();
}
