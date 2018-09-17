package huaxiaomi.pulan.com.mvp;

import huaxiaomi.pulan.com.mvp.i.IBaseModel;
import huaxiaomi.pulan.com.mvp.i.IBasePresent;
import huaxiaomi.pulan.com.mvp.i.IDailyModel;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.i.IDaoModel;
import huaxiaomi.pulan.com.mvp.i.ILoginModel;
import huaxiaomi.pulan.com.mvp.i.ILoginPresent;
import huaxiaomi.pulan.com.mvp.i.IMainModel;
import huaxiaomi.pulan.com.mvp.i.IMainPresent;
import huaxiaomi.pulan.com.mvp.i.IMessageDaoPresenter;
import huaxiaomi.pulan.com.mvp.i.IMessageModel;
import huaxiaomi.pulan.com.mvp.i.IMessagePresenter;
import huaxiaomi.pulan.com.mvp.i.IRecordModel;
import huaxiaomi.pulan.com.mvp.i.IRecordPresenter;
import huaxiaomi.pulan.com.mvp.m.BaseModel;
import huaxiaomi.pulan.com.mvp.m.DailyModel;
import huaxiaomi.pulan.com.mvp.m.DaoModel;
import huaxiaomi.pulan.com.mvp.m.LoginModel;
import huaxiaomi.pulan.com.mvp.m.MainModel;
import huaxiaomi.pulan.com.mvp.m.MessageModel;
import huaxiaomi.pulan.com.mvp.m.RecordModel;
import huaxiaomi.pulan.com.mvp.p.DailyPresenter;
import huaxiaomi.pulan.com.mvp.p.LoginPresenter;
import huaxiaomi.pulan.com.mvp.p.MainPresenter;
import huaxiaomi.pulan.com.mvp.p.MessageDaoPresenter;
import huaxiaomi.pulan.com.mvp.p.MessagePresenter;
import huaxiaomi.pulan.com.mvp.p.RecordPresenter;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 11:10
 */
@SuppressWarnings("unchecked")
public class MvpFactory {

    public static <P extends IBasePresent> P buildeP(Class<P> p){
        IBasePresent result = null;

        if(p == ILoginPresent.class){
            result = new LoginPresenter();
        }
        if(p == IMainPresent.class){
            result = new MainPresenter();
        }
        if(p == IMessagePresenter.class){
            result = new MessagePresenter();
        }
        if(p == IDailyPresenter.class){
            result = new DailyPresenter();
        }
        if(p == IRecordPresenter.class){
            result = new RecordPresenter();
        }
        if(p == IMessageDaoPresenter.class){
            result = new MessageDaoPresenter();
        }

        return (P) result;
    }

    public static <M extends IBaseModel> M buildeM(Class<M> m){
        BaseModel result = null;

        if(m == ILoginModel.class){
            result = new LoginModel();
        }
        if(m == IMainModel.class){
            result = new MainModel();
        }
        if(m == IMessageModel.class){
            result = new MessageModel();
        }
        if(m == IDailyModel.class){
            result = new DailyModel();
        }
        if(m == IRecordModel.class){
            result = new RecordModel();
        }
        if(m == IDaoModel.class){
            result = new DaoModel();
        }

        return (M) result;
    }
}
