package huaxiaomi.pulan.com.mvp.p;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import huaxiaomi.pulan.com.config.MessageType;
import huaxiaomi.pulan.com.http.DefaultNetCallBack;
import huaxiaomi.pulan.com.AudioManager;
import huaxiaomi.pulan.com.http.entity.Approval;
import huaxiaomi.pulan.com.http.entity.Attendance;
import huaxiaomi.pulan.com.http.entity.Dealt;
import huaxiaomi.pulan.com.http.entity.Meeting;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.http.entity.Pending;
import huaxiaomi.pulan.com.http.entity.Salary;
import huaxiaomi.pulan.com.http.entity.Saturation;
import huaxiaomi.pulan.com.http.entity.Schedule;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDaoModel;
import huaxiaomi.pulan.com.mvp.i.IMessageModel;
import huaxiaomi.pulan.com.mvp.i.IMessagePresenter;
import huaxiaomi.pulan.com.mvp.v.IMessageView;

/**
 * Description:
 * -
 * <p>
 * Author：chasen
 * Date： 2018/9/5 17:23
 */
public class MessagePresenter extends BasePresenter<IMessageView> implements IMessagePresenter {

    private IMessageModel messageModel;
    private IDaoModel daoModel;
    private Gson gson;

    public MessagePresenter() {
        messageModel = MvpFactory.buildeM(IMessageModel.class);
        daoModel = MvpFactory.buildeM(IDaoModel.class);
        gson = new Gson();
    }

    @Override
    public void sendTextMessage(String content) {
        Message<String> message = new Message<>();
        message.setWhoSpeak(0);
        message.setType(MessageType.TEXT);
        message.setResp(content);

        getProxyView().onMessageReceive(message);
        saveMessage(message);

        messageModel.sendTextMessage(message, new DefaultNetCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                Gson gson = new Gson();
                MsgRespond msgRespond = gson.fromJson(result, MsgRespond.class);
                String type = msgRespond.getType();

                Message rMessage = parseMessage(result, type);

                if (rMessage == null) {
                    getProxyView().showToast("意外的消息格式:" + type);
                    return;
                }

                if(rMessage.getResp() == null){
                    return;
                }

                if(rMessage.getResp() instanceof List){
                    List list = (List) rMessage.getResp();
                    if(list == null || list.size() == 0){
                        return;
                    }
                }

                rMessage.setWhoSpeak(1);
                if (MessageType.TEXT.equals(type)) {
                    String resp = (String) rMessage.getResp();
                    if (resp.length() <= 15) {
                        rMessage.setType(MessageType.VOICE);
                        messageModel.textToVoice(rMessage, this);
                        rMessage.setType(MessageType.TEXT);
                    }
                    getProxyView().onMessageReceive(rMessage);
                    saveMessage(rMessage);
                } else if (MessageType.VOICE.equals(type)) {
                    AudioManager.getInstance().play((String) rMessage.getResp());
                    if (!TextUtils.isEmpty(rMessage.getContent())) {
                        getProxyView().onMessageReceive(rMessage);
                        saveMessage(rMessage);
                    }
                } else {
                    getProxyView().onMessageReceive(rMessage);
                    saveMessage(rMessage);
                }

            }
        });
    }

    private void saveMessage(Message message) {
        if (message.getResp() instanceof String) {
            message.setDbData((String) message.getResp());
        } else {
            String json = gson.toJson(message.getResp());
            message.setDbData(json);
        }
        try {
            daoModel.save(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Message parseMessage(String result, String type) {

        Message rMessage = null;
        switch (type) {
            case MessageType.TEXT:
            case MessageType.TRY:
                rMessage = gson.fromJson(result, new TypeToken<Message<String>>() {
                }.getType());
                break;
            case MessageType.VOICE:
                rMessage = gson.fromJson(result, new TypeToken<Message<String>>() {
                }.getType());
                break;
            case MessageType.SALARY:
                rMessage = gson.fromJson(result, new TypeToken<Message<Salary>>() {
                }.getType());
                break;
            case MessageType.ATTENDANCE:
                rMessage = gson.fromJson(result, new TypeToken<Message<Attendance>>() {
                }.getType());
                break;
            case MessageType.MEETING:
                rMessage = gson.fromJson(result, new TypeToken<Message<List<Meeting>>>() {
                }.getType());
                break;
            case MessageType.SCHEDULE:
                rMessage = gson.fromJson(result, new TypeToken<Message<List<Schedule>>>() {
                }.getType());
                break;
            case MessageType.TO_READ:
                rMessage = gson.fromJson(result, new TypeToken<Message<List<Pending>>>() {
                }.getType());
                break;
            case MessageType.TO_DO:
                rMessage = gson.fromJson(result, new TypeToken<Message<List<Dealt>>>() {
                }.getType());
                break;
            case MessageType.SATURATION:
                rMessage = gson.fromJson(result, new TypeToken<Message<Saturation>>() {
                }.getType());
                break;
            case MessageType.APPROVAL:
                rMessage = gson.fromJson(result, new TypeToken<Message<List<Approval>>>() {
                }.getType());
                break;
        }
        return rMessage;
    }
}
