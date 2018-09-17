package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.activity.MeetingDetailActivity;
import huaxiaomi.pulan.com.http.entity.Meeting;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 9:51
 */
public class MeetingLayout extends LinearLayout {

    private TextView titleTv;
    private TextView descTv;
    private TextView timeTv;

    private Meeting meeting;

    public MeetingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_meeting,this);

        if(isInEditMode()){
            return;
        }

        titleTv = findViewById(R.id.title);
        descTv = findViewById(R.id.desc);
        timeTv = findViewById(R.id.time);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MeetingDetailActivity.class);
                intent.putExtra(MeetingDetailActivity.DATA_MEETING_ID,meeting);
                getContext().startActivity(intent);
            }
        });
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;

        titleTv.setText(meeting.getFd_subject());
        descTv.setText(meeting.getDoc_content());
        timeTv.setText(meeting.getStart_time());
    }
}
