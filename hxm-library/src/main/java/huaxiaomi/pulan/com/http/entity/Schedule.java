package huaxiaomi.pulan.com.http.entity;

/**
 * Description:日程安排实体类
 * <p>
 * Author: zcc
 * Date: 2018/9/9.
 */
public class Schedule {

    private String uuid;
    private String mail_name;
    private String calendar_date;
    private String doc_create_time;
    private String doc_subject;
    private String doc_start_time;
    private String doc_finish_time;
    private String fd_location;
    private String fd_status;

    public String getUuid() {
        return uuid;
    }

    public String getMail_name() {
        return mail_name;
    }

    public String getCalendar_date() {
        return calendar_date;
    }

    public String getDoc_create_time() {
        return doc_create_time;
    }

    public String getDoc_subject() {
        return doc_subject;
    }

    public String getDoc_start_time() {
        return doc_start_time;
    }

    public String getDoc_finish_time() {
        return doc_finish_time;
    }

    public String getFd_location() {
        return fd_location;
    }

    public String getFd_status() {
        return fd_status;
    }
}
