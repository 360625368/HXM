package huaxiaomi.pulan.com.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.http.entity.Pending;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;
import huaxiaomi.pulan.com.widget.PendingLayout;

/**
 * Description:待阅事项
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 16:06
 */
public class PendingActivity extends BaseActivity implements IDailyView<List<Pending>>{

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Pending,BaseViewHolder> adapter;
    private List<Pending> datas = new ArrayList<>();

    private IDailyPresenter dailyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_pending);

        recyclerView = findViewById(R.id.rv);

        adapter = new BaseQuickAdapter<Pending, BaseViewHolder>(R.layout.hxm_item_pending,datas) {
            @Override
            protected void convert(BaseViewHolder helper, Pending item) {
                PendingLayout pendingLayout = helper.getView(R.id.pending_layout);
                pendingLayout.setPending(item);
            }
        };
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.hxm_no_data_layout,null));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        dailyPresenter.getPending();
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
    public void onDailyMeesageResult(MsgRespond<List<Pending>> result) {
        datas.clear();
        datas.addAll(result.getResp());
        adapter.notifyDataSetChanged();
    }
}
