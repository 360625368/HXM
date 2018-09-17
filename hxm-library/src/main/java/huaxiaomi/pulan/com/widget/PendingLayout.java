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
import huaxiaomi.pulan.com.activity.PendingDetailActivity;
import huaxiaomi.pulan.com.http.entity.Pending;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 9:56
 */
public class PendingLayout extends LinearLayout {

    private TextView titleTv;
    private TextView descTv;
    private TextView timeTv;

    private Pending pending;

    public PendingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_pending,this);

        if(isInEditMode()){
            return;
        }

        titleTv = findViewById(R.id.title);
        descTv = findViewById(R.id.desc);
        timeTv = findViewById(R.id.time);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PendingDetailActivity.class);
                intent.putExtra(PendingDetailActivity.DATA_PENDING,pending);
                getContext().startActivity(intent);
            }
        });
    }

    public void setPending(Pending pending) {
        this.pending = pending;

        titleTv.setText(pending.getFd_type());
        descTv.setText(pending.getFd_subject());
        timeTv.setText(pending.getDoc_create_time().substring(5));
    }
}
