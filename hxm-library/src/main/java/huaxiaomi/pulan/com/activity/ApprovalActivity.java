package huaxiaomi.pulan.com.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.http.entity.Approval;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;
import huaxiaomi.pulan.com.widget.ApprovalLayout;

/**
 * Description:我的审批
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 16:33
 */
public class ApprovalActivity extends BaseActivity implements IDailyView<List<Approval>>{

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Approval,BaseViewHolder> adapter;
    private List<Approval> datas = new ArrayList<>();

    private IDailyPresenter dailyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_approval);

        recyclerView = findViewById(R.id.rv);

        adapter = new BaseQuickAdapter<Approval, BaseViewHolder>(R.layout.hxm_item_approval,datas) {
            @Override
            protected void convert(BaseViewHolder helper, Approval item) {
                ApprovalLayout approvalLayout = helper.getView(R.id.approval_layout);
                approvalLayout.setApproval(item);
            }
        };
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.hxm_no_data_layout,null));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = year + "-" + month + "-" + day;
        dailyPresenter.getApproval(date);
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
    public void onDailyMeesageResult(MsgRespond<List<Approval>> result) {
        datas.clear();
        datas.addAll(result.getResp());
        adapter.notifyDataSetChanged();
    }
}
