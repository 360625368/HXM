package huaxiaomi.pulan.com.mvp.v;


import huaxiaomi.pulan.com.http.entity.Message;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/5 17:23
 */
public interface IMessageView extends IBaseView {

    void onMessageReceive(Message result);
}
