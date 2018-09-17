package huaxiaomi.pulan.com.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;

import org.xutils.x;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.mvp.v.IBaseView;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:21
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        ImmersionBar.with(this).init();
        initPresenter();
        attachView();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showLongToast(String message) {
        ToastUtils.showLong(message);
    }

    @Override
    public void showProgressDialog(String title, String message) {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(true);
        }
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void showDefaultProgressDialog() {
        showProgressDialog(getString(R.string.hxm_loading_title),getString(R.string.hxm_loading_message));
    }

    @Override
    public void dismissProgressDialog() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dettachView();
        ImmersionBar.with(this).destroy();
    }
}
