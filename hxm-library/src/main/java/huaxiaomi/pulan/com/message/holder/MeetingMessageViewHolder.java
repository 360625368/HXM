package huaxiaomi.pulan.com.message.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Meeting;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.widget.MeetingLayout;

/**
 * Description:会议安排-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/12.
 */
public class MeetingMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<List<Meeting>>> {


    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_meeting;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<List<Meeting>> message) {
        RecyclerView recyclerView = helper.getView(R.id.rv);

        BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = new BaseQuickAdapter<Meeting, BaseViewHolder>(R.layout.hxm_item_meeting,new ArrayList<Meeting>()) {
                @Override
                protected void convert(BaseViewHolder helper, Meeting item) {
                    MeetingLayout meetingLayout = helper.getView(R.id.meeting_layout);
                    meetingLayout.setMeeting(item);
                }
            };
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(adapter);
        }

        List<Meeting> datas = adapter.getData();
        datas.clear();
        datas.addAll(message.getResp());
        adapter.notifyDataSetChanged();
    }
}
