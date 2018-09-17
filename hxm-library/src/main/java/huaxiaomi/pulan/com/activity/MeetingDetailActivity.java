package huaxiaomi.pulan.com.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.http.entity.Meeting;

/**
 * Description:会议安排详情
 * <p>
 * Author: zcc
 * Date: 2018/9/7.
 */
public class MeetingDetailActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Item, BaseViewHolder> adapter;
    private List<Item> datas = new ArrayList<>();

    private Meeting meeting;

    public static final String DATA_MEETING_ID = "DATA_MEETING_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_activity_meeting_detail);

        recyclerView = findViewById(R.id.rv);

        adapter = new BaseQuickAdapter<Item, BaseViewHolder>(R.layout.hxm_item_meeting_detail, datas) {
            @Override
            protected void convert(BaseViewHolder helper, Item item) {
                helper.setText(R.id.title, item.title);
                helper.setText(R.id.desc, item.desc);


                boolean lineUpVisible = helper.getAdapterPosition() != 0;
                boolean lineDownVisible = helper.getAdapterPosition() != datas.size() - 1;
                int circleColor = helper.getAdapterPosition() == 0 ? R.drawable.hxm_shape_circle_yellow : R.drawable.hxm_shape_circle_gray_light;
                helper.setVisible(R.id.line_up, lineUpVisible);
                helper.setVisible(R.id.line_down, lineDownVisible);
                helper.setBackgroundRes(R.id.circle,circleColor);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        meeting = (Meeting) getIntent().getSerializableExtra(DATA_MEETING_ID);
        if (meeting == null) {
            finish();
        }

        datas.add(buildeItem(meeting.getMail_name(), ""));
        datas.add(buildeItem("参会日期", meeting.getMeeting_date()));
        datas.add(buildeItem("会议主题", meeting.getFd_subject()));
        datas.add(buildeItem("开始时间", meeting.getStart_time()));
        datas.add(buildeItem("结束时间", meeting.getEnd_time()));
        datas.add(buildeItem("会议地点", meeting.getMeetingres_place()));
        datas.add(buildeItem("会议类型", meeting.getMeeting_type()));
        datas.add(buildeItem("会议主持人", meeting.getFd_host_person()));
        datas.add(buildeItem("会议参与人", meeting.getMeeting_attend_person()));

        adapter.notifyDataSetChanged();

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void dettachView() {

    }

    private Item buildeItem(String title, String desc) {
        Item item = new Item();
        item.title = title;
        item.desc = desc;
        return item;
    }

    private class Item {
        public String title;
        public String desc;
    }
}
