package huaxiaomi.pulan.com.message.holder;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.http.entity.Schedule;
import huaxiaomi.pulan.com.widget.ScheduleLayout;

/**
 * Description:日程安排-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/12.
 */
public class ScheduleMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<List<Schedule>>> {
    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_schedule;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<List<Schedule>> message) {
        ScheduleLayout scheduleLayout = helper.getView(R.id.schedule_layout);
        scheduleLayout.setData(message.getResp());
    }
}
