package huaxiaomi.pulan.com.mvp.i;

import huaxiaomi.pulan.com.mvp.v.IBaseView;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:13
 */
public interface IBasePresent<V extends IBaseView> {

    void attachView(V view);

    void dettachView();

    boolean isViewAttached();

    V getProxyView();
}
