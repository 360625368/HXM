package huaxiaomi.pulan.com.http.entity;

/**
 * Description:考勤查询实体类
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 17:30
 */
public class Attendance {

    private String date;                //考勤月份
    private String noPunch_times;       //未打卡次数
    private String name;                //员工姓名
    private String work_times;          //月度考勤时长
    private String late_times;          //月度迟到次数
    private String overtimes;           //加班时长
    private String leave_times;         //请假次数
    private String mail_name;           //登陆账号
    private String attend_rate;         //月度考勤率

    public String getDate() {
        return date;
    }

    public String getNoPunch_times() {
        return noPunch_times;
    }

    public String getName() {
        return name;
    }

    public String getWork_times() {
        return work_times;
    }

    public String getLate_times() {
        return late_times;
    }

    public String getOvertimes() {
        return overtimes;
    }

    public String getLeave_times() {
        return leave_times;
    }

    public String getMail_name() {
        return mail_name;
    }

    public String getAttend_rate() {
        return attend_rate;
    }
}
