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
import huaxiaomi.pulan.com.http.entity.Dealt;
import huaxiaomi.pulan.com.http.entity.DealtDetail;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;

/**
 * Description:待办事项详情
 * <p>
 * Author: zcc
 * Date: 2018/9/7.
 */
public class DealtDetailActivity extends BaseActivity implements IDailyView<List<DealtDetail>>{

    private TextView circleNameTv;
    private TextView nameTv;
    private TextView descTv;
    private TextView startTimeTv;
    private TextView endTimeTv;
    private TextView durationTv;
    private TextView contentLabelTv;
    private TextView contentTv;

    private RecyclerView recyclerView;
    private BaseQuickAdapter<DealtDetail,BaseViewHolder> adapter;
    private List<DealtDetail> datas = new ArrayList<>();

    private IDailyPresenter dailyPresenter;

    private Dealt dealt;

    public static final String DATA_DEALT = "DATA_DEALT" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_dealt_detail);

        circleNameTv = findViewById(R.id.circle_name);
        nameTv = findViewById(R.id.name);
        descTv = findViewById(R.id.desc);
        startTimeTv = findViewById(R.id.start_time);
        endTimeTv = findViewById(R.id.end_time);
        durationTv = findViewById(R.id.duration);
        contentLabelTv = findViewById(R.id.content_label);
        contentTv = findViewById(R.id.content);

        recyclerView = findViewById(R.id.rv);

        dealt = (Dealt) getIntent().getSerializableExtra(DATA_DEALT);

        if(dealt == null){
            return;
        }

        circleNameTv.setText(dealt.getFd_create_person());
        nameTv.setText(dealt.getFd_create_person());
        descTv.setText(dealt.getTodo_type());
        startTimeTv.setText(dealt.getDoc_create_time());
        endTimeTv.setText("缺少对应字段");
        durationTv.setText("缺少对应字段");
        contentTv.setText(dealt.getDoc_subject());

        adapter = new BaseQuickAdapter<DealtDetail, BaseViewHolder>(R.layout.hxm_item_dealt_detail,datas) {
            @Override
            protected void convert(BaseViewHolder helper, DealtDetail item) {
                helper.setText(R.id.name,item.getFd_handler_cn_name());
                helper.setText(R.id.time,item.getFd_handle_time());
                helper.setText(R.id.action,item.getFd_action_name());
                helper.setText(R.id.desc,item.getFd_from());
                helper.setText(R.id.content,item.getFd_action_info());

                boolean isLast = helper.getAdapterPosition() == datas.size() - 1;

                int circleRes = isLast ? R.mipmap.hxm_approval_pending : R.mipmap.hxm_submission;
                helper.setImageResource(R.id.circle,circleRes);

                boolean showLine = !isLast;
                helper.setGone(R.id.time,showLine);
                helper.setGone(R.id.line,showLine);
                helper.setGone(R.id.desc,showLine);

                int textColor = isLast ? R.color.hxm_orange : R.color.hxm_black;
                helper.setTextColor(R.id.name,getResources().getColor(textColor));
                helper.setTextColor(R.id.action,getResources().getColor(textColor));
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        dailyPresenter.getDealtDetail(dealt.getUuid());
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
    public void onDailyMeesageResult(MsgRespond<List<DealtDetail>> result) {
        datas.clear();
        datas.addAll(result.getResp());
        adapter.notifyDataSetChanged();
    }
}
