package huaxiaomi.pulan.com.mvp.p;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import huaxiaomi.pulan.com.mvp.i.IBasePresent;
import huaxiaomi.pulan.com.mvp.v.IBaseView;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:16
 */
public class BasePresenter<V extends IBaseView> implements IBasePresent {

    private V proxyView;
    private WeakReference<IBaseView> weakView;

    @Override
    public void attachView(IBaseView view) {
        weakView = new WeakReference<>(view);
        proxyView = initProxyView(view);
    }

    @Override
    public void dettachView() {
        if(weakView != null){
            weakView.clear();
            weakView = null;
        }
    }

    @Override
    public boolean isViewAttached() {
        return weakView != null && weakView.get() != null;
    }

    @Override
    public V getProxyView() {
        return proxyView;
    }

    @SuppressWarnings("unchecked")
    private V initProxyView(IBaseView view){
        return (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(isViewAttached()){
                    return method.invoke(weakView.get(),args);
                }
                return null;
            }
        });
    }
}
