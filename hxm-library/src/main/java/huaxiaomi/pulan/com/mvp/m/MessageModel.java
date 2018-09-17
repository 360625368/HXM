package huaxiaomi.pulan.com.mvp.m;

import huaxiaomi.pulan.com.config.HttpConfig;
import huaxiaomi.pulan.com.http.NetCallBack;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.mvp.i.IMessageModel;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/5 17:17
 */
public class MessageModel extends BaseModel implements IMessageModel {

    private static final String URL_DAILY_CHECK = HttpConfig.BASE_URL + "message/dplservertext";
    private static final String URL_TEXT_TO_VOICE = HttpConfig.BASE_URL + "message/plttsservice";

    @Override
    public void sendTextMessage(Message message, NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_DAILY_CHECK,message,callBack);
    }

    @Override
    public void textToVoice(Message message, NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_TEXT_TO_VOICE,message,callBack);
    }
}
