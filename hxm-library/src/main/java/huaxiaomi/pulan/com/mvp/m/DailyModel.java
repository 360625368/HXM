package huaxiaomi.pulan.com.mvp.m;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.config.HttpConfig;
import huaxiaomi.pulan.com.http.NetCallBack;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.mvp.i.IDailyModel;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 14:18
 */
public class DailyModel extends BaseModel implements IDailyModel {

    private static final String URL_SALARY_INFO = HttpConfig.BASE_URL + "message/getSalaryInfo";
    private static final String URL_ATTENDANCE = HttpConfig.BASE_URL + "message/getAttendance";
    private static final String URL_ATTENDANCE_RESULT = HttpConfig.BASE_URL + "message/getAttendanceResult";
    private static final String URL_MEETING = HttpConfig.BASE_URL + "message/meeting";
    private static final String URL_SCHEDULE = HttpConfig.BASE_URL + "message/schedule";
    private static final String URL_DEALT = HttpConfig.BASE_URL + "message/task";
    private static final String URL_DEALT_DETAIL = HttpConfig.BASE_URL + "message/taskapproval";
    private static final String URL_PENDING = HttpConfig.BASE_URL + "message/review";
    private static final String URL_PENDING_DETAIL = HttpConfig.BASE_URL + "message/reviewDetail";
    private static final String URL_WORKING_SATURATION = HttpConfig.BASE_URL + "message/saturation";
    private static final String URL_APPROVAL = HttpConfig.BASE_URL + "message/approvalprogress";
    private static final String URL_APPROVAL_DETAIL = HttpConfig.BASE_URL + "message/approvalDetail";
    private static final String URL_WORK_TASK = HttpConfig.BASE_URL + "message/workTask";

    @Override
    public List<DailyMenu> getDailyMenus() {
        List<DailyMenu> menus = new ArrayList<>();
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_salary, R.mipmap.hxm_salary, DailyMenu.Type.SALARY));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_attendance, R.mipmap.hxm_attendance, DailyMenu.Type.ATTENDANCE));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_meeting, R.mipmap.hxm_meeting, DailyMenu.Type.MEETING));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_schedule, R.mipmap.hxm_schedule, DailyMenu.Type.SCHEDULE));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_dealt, R.mipmap.hxm_dealt, DailyMenu.Type.DEALT));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_pending, R.mipmap.hxm_pending, DailyMenu.Type.PENDING));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_saturation, R.mipmap.hxm_working_saturation, DailyMenu.Type.WORKING_SATURATION));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_approval, R.mipmap.hxm_approval, DailyMenu.Type.APPROVAL));
        menus.add(buildeDailyMenu(R.string.hxm_daily_menu_task, R.mipmap.hxm_work_task, DailyMenu.Type.WORK_TASK));
        return menus;
    }

    @Override
    public void getSalary(NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_SALARY_INFO, "", callBack);
    }

    @Override
    public void getAttendance(NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_ATTENDANCE, "", callBack);
    }

    @Override
    public void getAttendance(String date, NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("date",date);
        getHttpClient().doPost(URL_ATTENDANCE, params, callBack);
    }

    @Override
    public void getAttendanceResult(String date,NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_ATTENDANCE_RESULT, "", callBack);
    }

    @Override
    public void getMeeting(NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_MEETING, "", callBack);
    }

    @Override
    public void getSchedule(String date ,NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("date",date);
        getHttpClient().doPost(URL_SCHEDULE, params, callBack);
    }

    @Override
    public void getDealt(String date,NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("date",date);
        getHttpClient().doPost(URL_DEALT, params, callBack);
    }

    @Override
    public void getDealtDetail(String id, NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("uuid",String.valueOf(id));
        getHttpClient().doPost(URL_DEALT_DETAIL,params,callBack);
    }

    @Override
    public void getPending(NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_PENDING, "", callBack);
    }

    @Override
    public void getPendingDetail(String id, NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("uuid",String.valueOf(id));
        getHttpClient().doPost(URL_PENDING_DETAIL,params,callBack);
    }

    @Override
    public void getWorkingSaturation(NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_WORKING_SATURATION, "", callBack);
    }

    @Override
    public void getWorkingSaturation(String date, NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("date",date);
        getHttpClient().doPost(URL_WORKING_SATURATION, params, callBack);
    }

    @Override
    public void getApproval(String date,NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("date",date);
        getHttpClient().doPost(URL_APPROVAL, params, callBack);
    }

    @Override
    public void getApprovalDetail(String id, NetCallBack callBack) {
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        getHttpClient().doPost(URL_APPROVAL_DETAIL, params, callBack);
    }

    @Override
    public void getWorkTask(NetCallBack callBack) {
        getHttpClient().doJsonPost(URL_WORK_TASK, "", callBack);
    }

    private DailyMenu buildeDailyMenu(@StringRes int titleRes, @DrawableRes int iconRes, DailyMenu.Type type) {
        DailyMenu menu = new DailyMenu();
        menu.setTitle(getContext().getString(titleRes));
        menu.setIconRes(iconRes);
        menu.setType(type);
        return menu;
    }
}
