package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.http.DefaultNetCallBack;
import huaxiaomi.pulan.com.http.entity.Attendance;
import huaxiaomi.pulan.com.http.entity.AttendanceResult;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.http.entity.Saturation;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyModel;
import huaxiaomi.pulan.com.utils.NumberUtils;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 10:03
 */
public class SaturationLayout extends LinearLayout {

    private TextView dateTv;
    private CalendarView calendarView;

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Item,BaseViewHolder> adapter;
    private List<Item> datas = new ArrayList<>();

    private Saturation saturation;

    private IDailyModel dailyModel;

    private Map<String, List<AttendanceResult>> attendanceResultMap = new HashMap<>();

    public SaturationLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_saturation,this);

        if(isInEditMode()){
            return;
        }

        dailyModel = MvpFactory.buildeM(IDailyModel.class);

        dateTv = findViewById(R.id.date);
        calendarView = findViewById(R.id.calendar_view);

        recyclerView = findViewById(R.id.rv);

        adapter = new BaseQuickAdapter<Item, BaseViewHolder>(R.layout.hxm_item_saturation,datas) {
            @Override
            protected void convert(BaseViewHolder helper, Item item) {

                RectHorizontalProgressBar progressBar = helper.getView(R.id.rhpb);

                helper.setText(R.id.title,item.title);
                helper.setText(R.id.desc,item.desc);
                progressBar.setProgressAnim(item.progress);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                if (!isClick) {
                    return;
                }

                String date = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                AttendanceMonthView.clickDate = date;
                calendarView.setSelected(false);
                dailyModel.getWorkingSaturation(date, new DefaultNetCallBack<MsgRespond<Saturation>>() {
                    @Override
                    public void onSuccess(MsgRespond<Saturation> result) {
                        super.onSuccess(result);
                        setSaturation(result.getResp());
                    }
                });
            }
        });

        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(final int year, final int month) {
                final String date = year + "-" + month;
                dateTv.setText(year + "年" + month + "月");
                if (attendanceResultMap.containsKey(date)) {
                    refreshCalendarView(year, month, attendanceResultMap.get(date));
                } else {
                    dailyModel.getAttendanceResult(date, new DefaultNetCallBack<MsgRespond<List<AttendanceResult>>>() {
                        @Override
                        public void onSuccess(MsgRespond<List<AttendanceResult>> result) {
                            super.onSuccess(result);
                            List<AttendanceResult> attendanceResults = result.getResp();
                            attendanceResultMap.put(date,attendanceResults);
                            refreshCalendarView(year, month, attendanceResults);
                        }
                    });
                }
            }
        });
    }

    public void setSaturation(Saturation saturation) {

        if(saturation == null){
            return;
        }
        this.saturation = saturation;

        try{
            int year = NumberUtils.toInt(saturation.getSat_date().split("-")[0],2018);
            int month = NumberUtils.toInt(saturation.getSat_date().split("-")[1],1);
            int day = NumberUtils.toInt(saturation.getSat_date().split("-")[2],1);
            calendarView.scrollToCalendar(year,month,day,true);
        }catch (Exception e){
            e.printStackTrace();
        }

        datas.clear();
        datas.add(buildeItem("考勤天数",saturation.getMon_kaoqin_days(), NumberUtils.toFloat(saturation.getMon_kaoqin_days(),0)));
        datas.add(buildeItem("会议时间(小时)",saturation.getMon_meeting_times(), NumberUtils.toFloat(saturation.getMon_meeting_times(),0)));
        datas.add(buildeItem("当天饱和度",saturation.getSaturation(), NumberUtils.toFloat(saturation.getSaturation().replace("%",""),0)));
        datas.add(buildeItem("月度平均饱和度",saturation.getMon_avg_saturation(), NumberUtils.toFloat(saturation.getMon_avg_saturation().replace("%",""),0)));

        calendarView.setSelected(true);
        adapter.notifyDataSetChanged();
    }

    private Item buildeItem(String title, String desc, float progress){
        Item item = new Item();
        item.title = title;
        item.desc = desc;
        item.progress = (int) progress;
        return item;
    }

    private void refreshCalendarView(int year, int month, List<AttendanceResult> attendanceResults) {

        if(attendanceResults == null){
            return;
        }

        Map<String, Calendar> map = new HashMap<>();

        for (AttendanceResult attendanceResult : attendanceResults) {
            int day = NumberUtils.toInt(attendanceResult.getKey(), 1);
            int color = "工作日".equals(attendanceResult.getValue()) ? R.color.hxm_black : R.color.hxm_red;
            int colorRes = getResources().getColor(color);


            Calendar calendar = getSchemeCalendar(year, month, day, colorRes, attendanceResult.getValue());
            map.put(calendar.toString(), calendar);
        }

        calendarView.setSchemeDate(map);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AttendanceMonthView.clickDate = "";
    }

    private class Item{
        public String title;
        public String desc;
        public int progress;
    }
}
