package huaxiaomi.pulan.com.mvp.i;

import java.util.List;

import huaxiaomi.pulan.com.http.NetCallBack;
import huaxiaomi.pulan.com.entity.DailyMenu;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 13:49
 */
public interface IDailyModel extends IBaseModel {

    List<DailyMenu> getDailyMenus();

    void getSalary(NetCallBack callBack);

    void getAttendance(NetCallBack callBack);

    void getAttendance(String date,NetCallBack callBack);

    void getAttendanceResult(String date,NetCallBack callBack);

    void getMeeting(NetCallBack callBack);

    void getSchedule(String date,NetCallBack callBack);

    void getDealt(String date, NetCallBack callBack);

    void getDealtDetail(String id, NetCallBack callBack);

    void getPending(NetCallBack callBack);

    void getPendingDetail(String id, NetCallBack callBack);

    void getWorkingSaturation(NetCallBack callBack);

    void getWorkingSaturation(String date,NetCallBack callBack);

    void getApproval(String date, NetCallBack callBack);

    void getApprovalDetail(String id, NetCallBack callBack);

    void getWorkTask(NetCallBack callBack);
}
