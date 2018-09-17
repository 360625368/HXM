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
import huaxiaomi.pulan.com.http.entity.Meeting;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyPresenter;
import huaxiaomi.pulan.com.mvp.v.IDailyView;
import huaxiaomi.pulan.com.widget.MeetingLayout;

/**
 * Description:会议安排
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 16:06
 */
public class MeetingActivity extends BaseActivity implements IDailyView<List<Meeting>>{

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Meeting,BaseViewHolder> adapter;
    private List<Meeting> datas = new ArrayList<>();

    private IDailyPresenter dailyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_meeting);

        recyclerView = findViewById(R.id.rv);

        adapter = new BaseQuickAdapter<Meeting, BaseViewHolder>(R.layout.hxm_item_meeting,datas) {
            @Override
            protected void convert(BaseViewHolder helper, Meeting item) {
                MeetingLayout meetingLayout = helper.getView(R.id.meeting_layout);
                meetingLayout.setMeeting(item);
            }
        };
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.hxm_no_data_layout,null));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        dailyPresenter.getMeeting();
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
    public void onDailyMeesageResult(MsgRespond<List<Meeting>> result) {
        datas.clear();
        datas.addAll(result.getResp());
        adapter.notifyDataSetChanged();

    }
}
