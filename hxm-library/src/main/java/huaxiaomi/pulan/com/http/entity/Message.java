package huaxiaomi.pulan.com.http.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import huaxiaomi.pulan.com.config.MessageType;

/**
 * Description:消息实体类
 * -
 *
 * Author：chasen
 * Date： 2018/9/5 17:14
 */
@Table(name = "Message")
public class Message<T> implements MultiItemEntity{

    @Column(name = "id",isId = true)
    private int id;
    @Column(name = "status")
    private int status;
    @Column(name = "error")
    private String error;
    @Column(name = "type")
    private String type;
    @Column(name = "who_speak")
    private int whoSpeak;       //0:自己  1:机器人
    @Column(name = "desc")
    private String desc;
    @Column(name = "edit_able")
    private boolean editAble;
    @Column(name = "content")
    private String content;
    private T resp;

    @Column(name = "db_data")
    private String dbData;      //消息内容是泛型，这里用dbData存储消息内容的json，待取出后再根据type将dbData转换成具体实体

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWhoSpeak() {
        return whoSpeak;
    }

    public void setWhoSpeak(int whoSpeak) {
        this.whoSpeak = whoSpeak;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isEditAble() {
        return editAble;
    }

    public void setEditAble(boolean editAble) {
        this.editAble = editAble;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public T getResp() {
        return resp;
    }

    public void setResp(T resp) {
        this.resp = resp;
    }

    public String getDbData() {
        return dbData;
    }

    public void setDbData(String dbData) {
        this.dbData = dbData;
    }
}
