package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.http.entity.Schedule;

/**
 * Description:
 * <p>
 * Author: zcc
 * Date: 2018/9/9.
 */
public class ScheduleItemLayout extends LinearLayout {

    private TextView timeTv;
    private TextView statusTv;
    private TextView descTv;

    private Schedule schedule;

    public ScheduleItemLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_schedule_item, this);

        if(isInEditMode()){
            return;
        }

        timeTv = findViewById(R.id.time);
        statusTv = findViewById(R.id.status);
        descTv = findViewById(R.id.desc);
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;

        String startTime = schedule.getDoc_start_time().split(" ")[1];
        String endTime = schedule.getDoc_finish_time().split(" ")[1];
        String status = schedule.getFd_status();

        timeTv.setText(startTime + "-" + endTime);
        descTv.setText(schedule.getDoc_subject());

        statusTv.setText(status);
        if ("已完成".equals(status)) {
            statusTv.setBackgroundColor(getResources().getColor(R.color.hxm_schedule_status_finish));
        } else {
            statusTv.setBackgroundColor(getResources().getColor(R.color.hxm_schedule_status_no_finish));
        }
    }
}
