package huaxiaomi.pulan.com.message.holder;

import com.chad.library.adapter.base.BaseViewHolder;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Attendance;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.widget.AttendanceLayout;
import huaxiaomi.pulan.com.widget.ChatLayout;

/**
 * Description:考勤查询-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/12.
 */
public class AttendanceMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<Attendance>> {
    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_attendance;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<Attendance> message) {
        AttendanceLayout attendanceLayout = helper.getView(R.id.attendance_layout);
        attendanceLayout.setAttendance(message.getResp());
    }
}
