package huaxiaomi.pulan.com.mvp.v;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 10:59
 */
public interface IRecordView extends IBaseView {

    void setVoiceLevel(int level);

    void setRecordEnable(boolean enable);

    void recordResult(String result);
}
