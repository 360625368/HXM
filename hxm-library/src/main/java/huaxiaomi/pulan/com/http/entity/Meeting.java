package huaxiaomi.pulan.com.http.entity;

import java.io.Serializable;

/**
 * Description:会议安排实体类
 * <p>
 * Author: zcc
 * Date: 2018/9/7.
 */
public class Meeting implements Serializable {

    private String uuid;                    //唯一标识
    private String mail_name;               //员工姓名
    private String meeting_date;            //开会日期
    private String start_time;              //开始时间
    private String end_time;                //结束时间
    private String fd_subject;              //会议名称
    private String doc_content;             //会议议题
    private String doc_create_time;         //创建时间
    private String doc_create_person;       //会议创建人
    private String doc_emcee_person;        //会议组织人
    private String fd_host_person;          //会议主持人
    private String doc_dept;                //会议组织部门
    private String meeting_type;            //会议类型
    private String meetingres_place;        //会议室地点
    private String meeting_copy_person;     //会议抄送人
    private String meeting_attend_person;   //会议参与人

    public String getUuid() {
        return uuid;
    }

    public String getMail_name() {
        return mail_name;
    }

    public String getMeeting_date() {
        return meeting_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getFd_subject() {
        return fd_subject;
    }

    public String getDoc_content() {
        return doc_content;
    }

    public String getDoc_create_time() {
        return doc_create_time;
    }

    public String getDoc_create_person() {
        return doc_create_person;
    }

    public String getDoc_emcee_person() {
        return doc_emcee_person;
    }

    public String getFd_host_person() {
        return fd_host_person;
    }

    public String getDoc_dept() {
        return doc_dept;
    }

    public String getMeeting_type() {
        return meeting_type;
    }

    public String getMeetingres_place() {
        return meetingres_place;
    }

    public String getMeeting_copy_person() {
        return meeting_copy_person;
    }

    public String getMeeting_attend_person() {
        return meeting_attend_person;
    }
}
