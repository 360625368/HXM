package huaxiaomi.pulan.com.http.entity;

import java.io.Serializable;

/**
 * Description:我的审批实体类
 * <p>
 * Author: zcc
 * Date: 2018/9/9.
 */
public class Approval implements Serializable{

    private String uuid;
    private String todo_type;
    private String fd_status;
    private String doc_create_time;
    private String fd_create_person;
    private String doc_subject;

    public String getUuid() {
        return uuid;
    }

    public String getTodo_type() {
        return todo_type;
    }

    public String getFd_status() {
        return fd_status;
    }

    public String getDoc_create_time() {
        return doc_create_time;
    }

    public String getFd_create_person() {
        return fd_create_person;
    }

    public String getDoc_subject() {
        return doc_subject;
    }
}
