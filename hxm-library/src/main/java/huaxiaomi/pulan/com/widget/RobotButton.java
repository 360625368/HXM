package huaxiaomi.pulan.com.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 10:27
 */
public class RobotButton extends LinearLayout implements View.OnTouchListener{

    private ImageView imageView;
    private float rawX;
    private float rawY;

    private boolean isMove = false;
    private boolean isUp = false;
    private boolean isLongClick = false;

    private Handler handler;

    private OnLongClickListener onLongClickListener;

    private static final int DOWN_DELAY = 300;

    public RobotButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_view_robot_button,this);

        if(isInEditMode()){
            return;
        }

        imageView = findViewById(R.id.icon);

        setOnTouchListener(this);

        handler = new Handler();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            rawX = event.getX();
            rawY = event.getY();

            isUp = false;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!isUp && !isMove && onLongClickListener != null){
                        isLongClick = true;
                        onLongClickListener.onLongClick();
                    }
                }
            },DOWN_DELAY);
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE && !isLongClick){


            float moveX = event.getX();
            float moveY = event.getY();

            if(!isMove){
                float offsetX = Math.abs(moveX - rawX);
                float offsetY = Math.abs(moveY - rawY);
                isMove = offsetX > 5 && offsetY > 5;
            }

            float targetX = getX() + moveX - rawX;
            float targetY = getY() + moveY - rawY;

            ViewGroup viewGroup = (ViewGroup) v.getParent();

            float horLimit = viewGroup.getMeasuredWidth() - getMeasuredWidth();
            float verLimit = viewGroup.getMeasuredHeight() - getMeasuredHeight();

            boolean overHor = horLimit < targetX;
            boolean overVer = verLimit < targetY;

            if(overHor){
                targetX = horLimit;
            }
            if(overVer){
                targetY = verLimit;
            }

            targetX = targetX < 0 ? 0 : targetX;
            targetY = targetY < 0 ? 0 : targetY;

            setX(targetX);
            setY(targetY);
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            if(isMove){
                float targetX = (getX() + getWidth() / 2) > (ScreenUtils.getScreenWidth() / 2) ? ScreenUtils.getScreenWidth() - getWidth() : 0;
                startAnima(targetX);
            }
            if(isLongClick){
                if(onLongClickListener != null){
                    onLongClickListener.onUp();
                }
            }

            isLongClick = false;
            isMove = false;
            isUp = true;
        }
        return true;
    }

    private void startAnima(float x){
        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"X",getX(),x);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public interface OnLongClickListener{
        void onLongClick();
        void onUp();
    }
}
