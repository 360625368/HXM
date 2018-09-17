package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 * Author：chasen
 * Date： 2018/9/13 15:50
 */
public class ChatLayout extends LinearLayout {

    private LinearLayout rootLayout;
    private FrameLayout groupLayout;

    private Direction direction = Direction.LEFT;

    public ChatLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_chat,this);

        rootLayout = findViewById(R.id.root);
        groupLayout = findViewById(R.id.group);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();View childView = getChildAt(1);

        if(childView != null){
            removeView(childView);
            groupLayout.addView(childView);
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;

        LayoutParams params = (LayoutParams) groupLayout.getLayoutParams();

        int bgRes = direction == Direction.LEFT ? R.drawable.hxm_left : R.drawable.hxm_right;
        float rightMargin = direction == Direction.LEFT ? getResources().getDimension(R.dimen.hxm_margin_max) : getResources().getDimension(R.dimen.hxm_margin_normal);
        float leftMargin = direction == Direction.LEFT ? getResources().getDimension(R.dimen.hxm_margin_normal) : getResources().getDimension(R.dimen.hxm_margin_max);
//        float leftPadding = direction == Direction.LEFT ? SizeUtils.dp2px(30) : getResources().getDimension(R.dimen.hxm_padding_normal);
//        float rightPadding = direction == Direction.LEFT ? getResources().getDimension(R.dimen.hxm_padding_normal) : SizeUtils.dp2px(20);

        rootLayout.setGravity(direction == Direction.LEFT ? Gravity.START : Gravity.END);

        params.rightMargin = (int) rightMargin;
        params.leftMargin = (int) leftMargin;

        groupLayout.setBackgroundResource(bgRes);
//        groupLayout.setPadding((int)leftPadding,groupLayout.getPaddingTop(),(int)rightPadding,groupLayout.getPaddingBottom());
    }

    public enum Direction{
        LEFT,
        RIGHT
    }

}
