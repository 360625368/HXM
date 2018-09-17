package huaxiaomi.pulan.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;

/**
 * Description:日常查询
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 11:36
 */
public class DailyActivity extends BaseActivity implements IDailyView<Object> {

    private RecyclerView recyclerView;
    private BaseQuickAdapter<DailyMenu,BaseViewHolder> adapter;
    private List<DailyMenu> menus = new ArrayList<>();

    private IDailyPresenter dailyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_daily);

        recyclerView = findViewById(R.id.rv);

        adapter = new BaseQuickAdapter<DailyMenu, BaseViewHolder>(R.layout.hxm_item_daily_menu,menus) {
            @Override
            protected void convert(BaseViewHolder helper, DailyMenu item) {
                helper.setText(R.id.title,item.getTitle());
                helper.setImageResource(R.id.icon,item.getIconRes());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DailyMenu menu = menus.get(position);
                Class tartActivity = null;
                switch (menu.getType()){
                    case SALARY:
//                        tartActivity = SalaryActivity.class;
                        showToast("暂未开放");
                        break;
                    case ATTENDANCE:
                        tartActivity = AttendanceActivity.class;
                        break;
                    case MEETING:
                        tartActivity = MeetingActivity.class;
                        break;
                    case DEALT:
                        tartActivity = DealtActivity.class;
                        break;
                    case PENDING:
                        tartActivity = PendingActivity.class;
                        break;
                    case APPROVAL:
                        tartActivity = ApprovalActivity.class;
                        break;
                    case SCHEDULE:
                        tartActivity = ScheduleAcitivty.class;
                        break;
                    case WORK_TASK:
//                        dailyPresenter.getWorkTask();
                        showToast("暂未开放");
                        break;
                    case WORKING_SATURATION:
                        tartActivity = SaturationActivity.class;
                        break;
                }

                if(tartActivity != null){
                    Intent intent = new Intent(DailyActivity.this,tartActivity);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);

        dailyPresenter.getDailyMenus();
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
        menus.clear();
        menus.addAll(dailyMenus);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDailyMeesageResult(MsgRespond result) {

    }
}
