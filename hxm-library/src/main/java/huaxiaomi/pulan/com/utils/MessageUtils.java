package huaxiaomi.pulan.com.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import huaxiaomi.pulan.com.config.MessageType;
import huaxiaomi.pulan.com.http.entity.Approval;
import huaxiaomi.pulan.com.http.entity.Attendance;
import huaxiaomi.pulan.com.http.entity.Dealt;
import huaxiaomi.pulan.com.http.entity.Meeting;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.http.entity.Pending;
import huaxiaomi.pulan.com.http.entity.Salary;
import huaxiaomi.pulan.com.http.entity.Saturation;
import huaxiaomi.pulan.com.http.entity.Schedule;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 14:33
 */
public class MessageUtils {

    /**
     * 将数据库查出来的message数据转成具体实体
     * @param message
     * @param gson
     * @return
     */
    public static Message initRawMessage(Message message, Gson gson){
        String json = message.getDbData();
        String type = message.getType();
        Object resp = null;
        switch (type){
            case MessageType.TEXT:
            case MessageType.TRY:
            case MessageType.VOICE:
                resp = message.getDbData();
                break;
            case MessageType.SALARY:
                resp = gson.fromJson(json,Salary.class);
                break;
            case MessageType.ATTENDANCE:
                resp = gson.fromJson(json,Attendance.class);
                break;
            case MessageType.MEETING:
                resp = gson.fromJson(json,Meeting.class);
                break;
            case MessageType.SCHEDULE:
                resp = gson.fromJson(json,new TypeToken<List<Schedule>>(){}.getType());
                break;
            case MessageType.TO_READ:
                resp = gson.fromJson(json,new TypeToken<List<Pending>>(){}.getType());
                break;
            case MessageType.TO_DO:
                resp = gson.fromJson(json,new TypeToken<List<Dealt>>(){}.getType());
                break;
            case MessageType.SATURATION:
                resp = gson.fromJson(json,Saturation.class);
                break;
            case MessageType.APPROVAL:
                resp = gson.fromJson(json,new TypeToken<List<Approval>>(){}.getType());
                break;
        }
        message.setResp(resp);
        return message;
    }
}
