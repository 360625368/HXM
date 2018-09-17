package huaxiaomi.pulan.com.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.http.entity.Attendance;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;
import huaxiaomi.pulan.com.widget.AttendanceLayout;

/**
 * Description:考勤查询
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 14:16
 */
public class AttendanceActivity extends BaseActivity implements IDailyView<Attendance> {

    private AttendanceLayout attendanceLayout;

    private IDailyPresenter dailyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_attendance);

        attendanceLayout = findViewById(R.id.attendance_layout);

        dailyPresenter.getAttendance();
    }

    @Override
    public void initPresenter() {
        dailyPresenter = MvpFactory.buildeP(IDailyPresenter.class);
    }

    @Override
    public void attachView() {
        dailyPresenter.attachView(this);
    }

    @Override
    public void dettachView() {
        dailyPresenter.dettachView();
    }

    @Override
    public void initDailyMenu(List<DailyMenu> dailyMenus) {

    }

    @Override
    public void onDailyMeesageResult(MsgRespond<Attendance> result) {
        attendanceLayout.setAttendance(result.getResp());
    }
}
