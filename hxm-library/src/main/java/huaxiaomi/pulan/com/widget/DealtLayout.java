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
import huaxiaomi.pulan.com.activity.DealtDetailActivity;
import huaxiaomi.pulan.com.http.entity.Dealt;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 9:44
 */
public class DealtLayout extends LinearLayout{

    private TextView titleTv;
    private TextView timeTv;
    private TextView contentTv;

    private Dealt dealt;

    public DealtLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_dealt,this);

        if(isInEditMode()){
            return;
        }

        titleTv = findViewById(R.id.title);
        timeTv = findViewById(R.id.time);
        contentTv = findViewById(R.id.content);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dealt == null){
                    return;
                }
                Intent intent = new Intent(getContext(),DealtDetailActivity.class);
                intent.putExtra(DealtDetailActivity.DATA_DEALT,dealt);
                getContext().startActivity(intent);
            }
        });
    }

    public void setDealt(Dealt dealt) {
        this.dealt = dealt;
        titleTv.setText(dealt.getDoc_subject());
        contentTv.setText(dealt.getTodo_type());
        timeTv.setText(dealt.getDoc_create_time().substring(5));
    }
}
