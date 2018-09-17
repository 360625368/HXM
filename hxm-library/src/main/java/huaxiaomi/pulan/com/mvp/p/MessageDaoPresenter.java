package huaxiaomi.pulan.com.mvp.p;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDaoModel;
import huaxiaomi.pulan.com.mvp.i.IMessageDaoPresenter;
import huaxiaomi.pulan.com.mvp.v.IMessageDaoView;
import huaxiaomi.pulan.com.utils.MessageUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 15:07
 */
public class MessageDaoPresenter extends BasePresenter<IMessageDaoView> implements IMessageDaoPresenter {

    private IDaoModel daoModel;
    private Gson gson;

    public MessageDaoPresenter() {
        daoModel = MvpFactory.buildeM(IDaoModel.class);
        gson = new Gson();
    }

    @Override
    public void findMessageFromDao(final int from, final int to, final boolean desc) {
        try {
            Disposable disposable = Observable
                    .create(new ObservableOnSubscribe<List<Message>>() {
                        @Override
                        public void subscribe(ObservableEmitter<List<Message>> emitter) throws Exception {
                            List<Message> rawMessages = daoModel.find(Message.class, from, to, "id", desc);

                            if (rawMessages == null) {
                                emitter.onNext(new ArrayList<Message>());
                                return;
                            }
                            List<Message> messages = new ArrayList<>();
                            for (Message rawMessage : rawMessages) {
                                Message temp = MessageUtils.initRawMessage(rawMessage, gson);
                                if(temp == null || temp.getResp() == null){
                                    continue;
                                }
                                messages.add(temp);
                            }
                            emitter.onNext(messages);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Message>>() {
                        @Override
                        public void accept(List<Message> messages) throws Exception {
                            getProxyView().onMessageFromDao(messages);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllMessage() {
        try {
            daoModel.delete(Message.class);
            getProxyView().onDeleteAllMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
