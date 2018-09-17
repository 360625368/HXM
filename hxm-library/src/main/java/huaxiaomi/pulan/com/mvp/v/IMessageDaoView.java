package huaxiaomi.pulan.com.mvp.v;

import java.util.List;

import huaxiaomi.pulan.com.http.entity.Message;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 14:50
 */
public interface IMessageDaoView extends IBaseView {

    void onMessageFromDao(List<Message> messages);

    void onDeleteAllMessage();
}
