package huaxiaomi.pulan.com.http;

import com.blankj.utilcode.util.ToastUtils;

import huaxiaomi.pulan.com.config.HttpConfig;
import huaxiaomi.pulan.com.http.entity.MsgRespond;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 18:02
 */
public abstract class DefaultNetCallBack<T> implements NetCallBack<T> {

    @Override
    public void onSuccess(T result) {
        if(result instanceof MsgRespond){
            if(HttpConfig.RESULT_OK != ((MsgRespond) result).getStatus()){
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        ToastUtils.showShort("数据异常");
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFinish() {

    }
}
