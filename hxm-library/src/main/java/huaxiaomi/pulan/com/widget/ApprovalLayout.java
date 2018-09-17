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
import huaxiaomi.pulan.com.activity.ApprovalDetailActivity;
import huaxiaomi.pulan.com.http.entity.Approval;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 9:26
 */
public class ApprovalLayout extends LinearLayout {

    private TextView titleTv;
    private TextView descTv;
    private TextView statusTv;

    private Approval approval;

    public ApprovalLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_approval,this);

        if(isInEditMode()){
            return;
        }

        titleTv = findViewById(R.id.title);
        descTv = findViewById(R.id.desc);
        statusTv = findViewById(R.id.status);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(approval == null){
                    return;
                }
                Intent intent = new Intent(getContext(),ApprovalDetailActivity.class);
                intent.putExtra(ApprovalDetailActivity.DATA_APPROVAL,approval);
                getContext().startActivity(intent);
            }
        });
    }

    public void setApproval(Approval approval) {
        this.approval = approval;
        titleTv.setText(approval.getTodo_type());
        descTv.setText(approval.getDoc_subject());
//        statusTv.setText(approval.getFd_status());
    }
}
