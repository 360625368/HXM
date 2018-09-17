package huaxiaomi.pulan.com.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 14:30
 */
public class TitlebarLayout extends LinearLayout {

    private TextView titleTv;
    private ImageView moreIv;
    private LinearLayout backLayout;

    private String title;
    private boolean showMore;
    private boolean showBack;

    private OnMoreClickListener moreClickListener;

    public TitlebarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_titlebar,this);

        if(isInEditMode()){
            return;
        }

        titleTv = findViewById(R.id.title);
        moreIv = findViewById(R.id.more);
        backLayout = findViewById(R.id.back_layout);

        moreIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moreClickListener != null){
                    moreClickListener.onMoreClick(v);
                }
            }
        });
        backLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getContext() instanceof Activity){
                    ((Activity) getContext()).finish();
                }
            }
        });

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.TitlebarLayout);
        title = array.getString(R.styleable.TitlebarLayout_TitlebarLayout_title);
        showMore = array.getBoolean(R.styleable.TitlebarLayout_TitlebarLayout_showMore,false);
        showBack = array.getBoolean(R.styleable.TitlebarLayout_TitlebarLayout_showBack,false);
        array.recycle();

        titleTv.setText(title);
        backLayout.setVisibility(showBack ? VISIBLE : INVISIBLE);
        moreIv.setVisibility(showMore ? VISIBLE : INVISIBLE);
    }

    public void setTitle(String title){
        titleTv.setText(title);
    }

    public void setMoreClickListener(OnMoreClickListener moreClickListener) {
        this.moreClickListener = moreClickListener;
    }

    public interface OnMoreClickListener{
        void onMoreClick(View view);
    }
}
