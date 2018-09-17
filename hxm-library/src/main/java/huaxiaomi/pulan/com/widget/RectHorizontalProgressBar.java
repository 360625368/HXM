package huaxiaomi.pulan.com.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/7 16:51
 */
public class RectHorizontalProgressBar extends View {


    private int max;
    private int progress;
    private int color;
    private int uncheckColor;

    private Paint paint;
    private Rect rect;

    private static final long ANIMATION_DUATION = 1000;

    public RectHorizontalProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.RectHorizontalProgressBar);

        max = array.getInt(R.styleable.RectHorizontalProgressBar_RectHorizontalProgressBar_Max,100);
        progress = array.getInt(R.styleable.RectHorizontalProgressBar_RectHorizontalProgressBar_Progress,100);
        color = array.getColor(R.styleable.RectHorizontalProgressBar_RectHorizontalProgressBar_Color,getResources().getColor(R.color.hxm_yellow));
        uncheckColor = array.getColor(R.styleable.RectHorizontalProgressBar_RectHorizontalProgressBar_UncheckColor,getResources().getColor(R.color.hxm_gray_light));

        array.recycle();

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        paint.setColor(uncheckColor);
        rect.set(0,0,width,height);
        canvas.drawRect(rect,paint);

        int right = (int) (((float)progress / max) * width);
        paint.setColor(color);
        rect.set(0,0,right,height);
        canvas.drawRect(rect,paint);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setProgressAnim(int progress){
        progress = progress > 100 ? 100 : progress;
        ObjectAnimator animator = ObjectAnimator.ofInt(this,"progress",this.progress,progress);
        animator.setDuration(ANIMATION_DUATION);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }
}
