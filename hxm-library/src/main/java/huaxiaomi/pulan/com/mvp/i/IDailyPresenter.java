package huaxiaomi.pulan.com.mvp.i;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 13:49
 */
public interface IDailyPresenter extends IBasePresent {

    void getDailyMenus();

    void getSalary();

    void getAttendance();

    void getAttendanceResult(String date);

    void getMeeting();

    void getSchedule(String date);

    void getDealt(String date);

    void getDealtDetail(String id);

    void getPending();

    void getPendingDetail(String id);

    void getWorkingSaturation();

    void getApproval(String date);

    void getApprovalDetail(String id);

    void getWorkTask();
}
