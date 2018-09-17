package huaxiaomi.pulan.com.mvp.i;

import huaxiaomi.pulan.com.http.NetCallBack;
import huaxiaomi.pulan.com.http.entity.Message;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/5 16:32
 */
public interface IMessageModel extends IBaseModel {

    void sendTextMessage(Message message, NetCallBack callBack);

    void textToVoice(Message message,NetCallBack callBack);
}
