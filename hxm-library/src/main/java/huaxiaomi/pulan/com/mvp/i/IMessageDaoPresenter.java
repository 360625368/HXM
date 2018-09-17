package huaxiaomi.pulan.com.mvp.i;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 14:50
 */
public interface IMessageDaoPresenter extends IBasePresent {

    void findMessageFromDao(int from,int to,boolean desc);

    void deleteAllMessage();
}
