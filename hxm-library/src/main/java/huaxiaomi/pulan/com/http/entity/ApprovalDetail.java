package huaxiaomi.pulan.com.http.entity;

/**
 * Description:我的审批流程节点实体类
 * <p>
 * Author: zcc
 * Date: 2018/9/9.
 */
public class ApprovalDetail {

    private String fd_id;
    private String fd_action_name;
    private String fd_action_info;
    private String fd_handler_cn_name;
    private String fd_handle_time;

    public String getFd_id() {
        return fd_id;
    }

    public String getFd_action_name() {
        return fd_action_name;
    }

    public String getFd_action_info() {
        return fd_action_info;
    }

    public String getFd_handler_cn_name() {
        return fd_handler_cn_name;
    }

    public String getFd_handle_time() {
        return fd_handle_time;
    }
}
