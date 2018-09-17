package huaxiaomi.pulan.com.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.http.entity.Pending;
import huaxiaomi.pulan.com.http.entity.PendingDetail;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;

/**
 * Description:待阅事项详情
 * <p>
 * Author: zcc
 * Date: 2018/9/9.
 */
public class PendingDetailActivity extends BaseActivity implements IDailyView<PendingDetail> {

    private TextView circleNameTv;
    private TextView nameTv;
    private TextView descTv;
    private TextView startTimeTv;
    private TextView endTimeTv;
    private TextView durationTv;
    private TextView contentLabelTv;
    private TextView contentTv;

    private RecyclerView recyclerView;
    private BaseQuickAdapter<PendingDetail,BaseViewHolder> adapter;
    private List<PendingDetail> datas = new ArrayList<>();

    private IDailyPresenter dailyPresenter;

    private Pending pending;

    public static final String DATA_PENDING = "DATA_PENDING";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_pending_detail);

        circleNameTv = findViewById(R.id.circle_name);
        nameTv = findViewById(R.id.name);
        descTv = findViewById(R.id.desc);
        startTimeTv = findViewById(R.id.start_time);
        endTimeTv = findViewById(R.id.end_time);
        durationTv = findViewById(R.id.duration);
        contentLabelTv = findViewById(R.id.content_label);
        contentTv = findViewById(R.id.content);

        recyclerView = findViewById(R.id.rv);

        pending = (Pending) getIntent().getSerializableExtra(DATA_PENDING);

        if(pending == null){
            return;
        }

        circleNameTv.setText(pending.getMail_name());
        nameTv.setText(pending.getMail_name());
//        descTv.setText(pending.getFd_status());
        startTimeTv.setText(pending.getDoc_create_time());
        endTimeTv.setText("缺少对应字段");
        durationTv.setText("缺少对应字段");
        contentTv.setText(pending.getFd_subject());

        adapter = new BaseQuickAdapter<PendingDetail, BaseViewHolder>(R.layout.hxm_item_pending_detail,datas) {
            @Override
            protected void convert(BaseViewHolder helper, PendingDetail item) {
                boolean lineVisibile = helper.getAdapterPosition() != datas.size() - 1;
                helper.setGone(R.id.line,lineVisibile);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        dailyPresenter.getPendingDetail(pending.getUuid());

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
    public void onDailyMeesageResult(MsgRespond<PendingDetail> result) {
        PendingDetail pendingDetail = result.getResp();

        nameTv.setText(pendingDetail.getMail_name());
        descTv.setText(pendingDetail.getFd_status());
        startTimeTv.setText(pendingDetail.getDoc_create_time());
        endTimeTv.setText("缺少对应字段");
        durationTv.setText("缺少对应字段");
        contentTv.setText(pendingDetail.getFd_subject());
    }
}
