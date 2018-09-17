package huaxiaomi.pulan.com.http.entity;

/**
 * Description:工作饱和度实体类
 * <p>
 * Author: zcc
 * Date: 2018/9/9.
 */
public class Saturation {

    private String mon_meeting_times;
    private String saturation;
    private String month;
    private String mon_avg_saturation;
    private String sat_date;
    private String mail_name;
    private String mon_kaoqin_days;

    public String getMon_meeting_times() {
        return mon_meeting_times;
    }

    public String getSaturation() {
        return saturation;
    }

    public String getMonth() {
        return month;
    }

    public String getMon_avg_saturation() {
        return mon_avg_saturation;
    }

    public String getSat_date() {
        return sat_date;
    }

    public String getMail_name() {
        return mail_name;
    }

    public String getMon_kaoqin_days() {
        return mon_kaoqin_days;
    }
}
