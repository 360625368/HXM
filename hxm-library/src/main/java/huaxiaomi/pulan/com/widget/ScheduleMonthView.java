package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

import huaxiaomi.pulan.com.R;

/**
 * 高仿魅族日历布局
 * Created by huanghaibin on 2017/11/15.
 */

public class ScheduleMonthView extends MonthView {

    private int mRadius;
    public static  String clickDate = "";

    public ScheduleMonthView(Context context) {
        super(context);

        //兼容硬件加速无效的代码
        setLayerType(View.LAYER_TYPE_SOFTWARE, mSelectedPaint);
        //4.0以上硬件加速会导致无效
//        mSelectedPaint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID));

        setLayerType(View.LAYER_TYPE_SOFTWARE, mSchemePaint);
//        mSchemePaint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID));
    }

    @Override
    protected void onPreviewHook() {
        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;
    }

    /**
     * 如果需要点击Scheme没有效果，则return true
     *
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param y         日历Card y起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return false 则不绘制onDrawScheme，因为这里背景色是互斥的
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        String date = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
        boolean isClickDate = date.equals(clickDate);
        if(isClickDate || calendar.isCurrentDay()){
            int cx = x + mItemWidth / 2;
            int cy = y + mItemHeight / 2;
//            canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
            canvas.drawRect(x,y,x + mItemWidth,y +mItemHeight,mSelectedPaint);
        }
        return false;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
//        canvas.drawCircle(cx, cy, mRadius, mSchemePaint);
        canvas.drawRect(x,y,x + mItemWidth,y + mItemHeight,mSchemePaint);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        float cy = y + mTextBaseLine;
        String date = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
        boolean isClickDate = date.equals(clickDate);
        if (isSelected && (isClickDate || calendar.isCurrentDay())) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, cy,
                    mSelectTextPaint);
        } else if(calendar.getWeek() == 0){
            int rawColor = mSelectTextPaint.getColor();
            mSelectTextPaint.setColor(getResources().getColor(R.color.hxm_red));
            canvas.drawText(String.valueOf(calendar.getDay()), cx, cy,
                    mSelectTextPaint);
            mSelectTextPaint.setColor(rawColor);
        }else if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, cy,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, cy,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }
}
