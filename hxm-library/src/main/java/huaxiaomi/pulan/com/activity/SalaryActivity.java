package huaxiaomi.pulan.com.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.http.entity.Salary;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;
import huaxiaomi.pulan.com.widget.SalaryLayout;

/**
 * Description:薪资查询
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 11:08
 */
public class SalaryActivity extends BaseActivity implements IDailyView<Salary> {

    private SalaryLayout salaryLayout;

    private IDailyPresenter dailyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_salary);

        salaryLayout = findViewById(R.id.salary_layout);

        dailyPresenter.getSalary();
    }

    @Override
    public void initDailyMenu(List<DailyMenu> dailyMenus) {

    }

    @Override
    public void onDailyMeesageResult(MsgRespond<Salary> result) {
        salaryLayout.setSalary(result.getResp());
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
}
