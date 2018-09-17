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
import huaxiaomi.pulan.com.http.entity.Approval;
import huaxiaomi.pulan.com.http.entity.ApprovalDetail;
import huaxiaomi.pulan.com.entity.DailyMenu;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;

/**
 * Description:我的审批详情页
 * <p>
 * Author: zcc
 * Date: 2018/9/9.
 */
public class ApprovalDetailActivity extends BaseActivity implements IDailyView<List<ApprovalDetail>> {

    private TextView circleNameTv;
    private TextView nameTv;
    private TextView descTv;
    private TextView startTimeTv;
    private TextView endTimeTv;
    private TextView durationTv;
    private TextView contentLabelTv;
    private TextView contentTv;

    private RecyclerView recyclerView;
    private BaseQuickAdapter<ApprovalDetail,BaseViewHolder> adapter;
    private List<ApprovalDetail> datas = new ArrayList<>();

    private IDailyPresenter dailyPresenter;

    private Approval approval;

    public static final String DATA_APPROVAL = "DATA_APPROVAL";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_approval_detail);

        circleNameTv = findViewById(R.id.circle_name);
        nameTv = findViewById(R.id.name);
        descTv = findViewById(R.id.desc);
        startTimeTv = findViewById(R.id.start_time);
        endTimeTv = findViewById(R.id.end_time);
        durationTv = findViewById(R.id.duration);
        contentLabelTv = findViewById(R.id.content_label);
        contentTv = findViewById(R.id.content);

        recyclerView = findViewById(R.id.rv);

        approval = (Approval) getIntent().getSerializableExtra(DATA_APPROVAL);

        if(approval == null){
            return;
        }

        circleNameTv.setText(approval.getFd_create_person());
        nameTv.setText(approval.getFd_create_person());
//        descTv.setText(approval.getFd_status());
        startTimeTv.setText(approval.getDoc_create_time());
        endTimeTv.setText("缺少对应字段");
        durationTv.setText("缺少对应字段");
        contentTv.setText(approval.getDoc_subject());

        adapter = new BaseQuickAdapter<ApprovalDetail, BaseViewHolder>(R.layout.hxm_item_approval_detail,datas) {
            @Override
            protected void convert(BaseViewHolder helper, ApprovalDetail item) {

                String name = item.getFd_handler_cn_name();
                if(name.length() > 2){
                    name = name.substring(name.length() - 2,name.length());
                }

                helper.setText(R.id.name,name);
                helper.setText(R.id.title,item.getFd_handler_cn_name());
                helper.setText(R.id.status,item.getFd_action_name());
                helper.setText(R.id.time,item.getFd_handle_time());
                helper.setText(R.id.info,item.getFd_action_info());

                boolean lineVisibile = helper.getAdapterPosition() != datas.size() - 1;
                helper.setGone(R.id.line,lineVisibile);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        dailyPresenter.getApprovalDetail(approval.getUuid());
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
    public void onDailyMeesageResult(MsgRespond<List<ApprovalDetail>> result) {
        datas.clear();
        datas.addAll(result.getResp());
        adapter.notifyDataSetChanged();
    }
}
