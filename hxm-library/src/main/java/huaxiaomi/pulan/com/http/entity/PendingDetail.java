package huaxiaomi.pulan.com.http.entity;

/**
 * Description:待阅事项详情实体类
 * <p>
 * Author: zcc
 * Date: 2018/9/10.
 */
public class PendingDetail {

    private String uuid;
    private String mail_name;
    private String doc_create_time;
    private String fd_type;
    private String fd_subject;
    private String fd_status;

    public String getUuid() {
        return uuid;
    }

    public String getMail_name() {
        return mail_name;
    }

    public String getDoc_create_time() {
        return doc_create_time;
    }

    public String getFd_type() {
        return fd_type;
    }

    public String getFd_subject() {
        return fd_subject;
    }

    public String getFd_status() {
        return fd_status;
    }
}
