package huaxiaomi.pulan.com.message;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import huaxiaomi.pulan.com.config.MessageType;

/**
 * Description:
 * <p>
 * Author: zcc
 * Date: 2018/9/11.
 */
public class MessageMultipleItem<T> implements MultiItemEntity {

    private int status;
    private String type;
    private String error;
    private int whoSpeak;       //0:自己  1:机器人
    private T resp;

    public static final int TYPE_TEXT = 1;
    public static final int TYPE_VOICE = 2;
    public static final int TYPE_SALARY = 3;
    public static final int TYPE_ATTENDANCE = 4;
    public static final int TYPE_MEETING = 5;
    public static final int TYPE_SCHEDULE = 6;
    public static final int TYPE_TO_READ = 7;
    public static final int TYPE_TO_DO = 8;
    public static final int TYPE_SATURATION = 9;
    public static final int TYPE_APPROVAL = 10;

    @Override
    public int getItemType() {
        switch (type){
            case MessageType.TEXT:
            case MessageType.TRY:
                return TYPE_TEXT;
            case MessageType.VOICE:
                return TYPE_VOICE;
            case MessageType.SALARY:
                return TYPE_SALARY;
            case MessageType.ATTENDANCE:
                return TYPE_ATTENDANCE;
            case MessageType.MEETING:
                return TYPE_MEETING;
            case MessageType.SCHEDULE:
                return TYPE_SCHEDULE;
            case MessageType.TO_READ:
                return TYPE_TO_READ;
            case MessageType.TO_DO:
                return TYPE_TO_DO;
            case MessageType.SATURATION:
                return TYPE_SATURATION;
            case MessageType.APPROVAL:
                return TYPE_APPROVAL;
        }
        return TYPE_TEXT;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getWhoSpeak() {
        return whoSpeak;
    }

    public void setWhoSpeak(int whoSpeak) {
        this.whoSpeak = whoSpeak;
    }

    public T getResp() {
        return resp;
    }

    public void setResp(T resp) {
        this.resp = resp;
    }
}
