package huaxiaomi.pulan.com.mvp.i;

import android.content.Context;

import com.baidu.speech.EventListener;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 11:01
 */
public interface IRecordModel extends IBaseModel {

    void init(Context context);

    void register(EventListener eventListener);

    void unregister(EventListener eventListener);

    void startRecord();

    void stopRecord();
}
