package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.http.DefaultNetCallBack;
import huaxiaomi.pulan.com.http.entity.MsgRespond;
import huaxiaomi.pulan.com.http.entity.Schedule;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IDailyModel;
import huaxiaomi.pulan.com.utils.NumberUtils;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 10:15
 */
public class ScheduleLayout extends LinearLayout {

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Schedule,BaseViewHolder> adapter;
    private List<Schedule> datas = new ArrayList<>();

    private TextView yearTv;
    private TextView monthTv;

    private CalendarView calendarView;

    private IDailyModel dailyModel;

    public ScheduleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_schedule,this);

        if(isInEditMode()){
            return;
        }

        dailyModel = MvpFactory.buildeM(IDailyModel.class);

        recyclerView = findViewById(R.id.rv);

        yearTv = findViewById(R.id.year);
        monthTv = findViewById(R.id.month);

        calendarView = findViewById(R.id.calendar_view);

        TextView todayTv = findViewById(R.id.today);
        todayTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.scrollToCurrent();
            }
        });

        adapter = new BaseQuickAdapter<Schedule, BaseViewHolder>(R.layout.hxm_item_schedule,datas) {
            @Override
            protected void convert(BaseViewHolder helper, Schedule item) {
                ScheduleItemLayout scheduleItemLayout = helper.getView(R.id.schedule_item_layout);
                scheduleItemLayout.setSchedule(item);
            }
        };
        adapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.hxm_no_data_layout,null));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {

                String date = year + "-" + month;

                yearTv.setText(String.valueOf(year));
                monthTv.setText(String.valueOf(month));

                dailyModel.getSchedule(date, new DefaultNetCallBack<MsgRespond<List<Schedule>>>() {
                    @Override
                    public void onSuccess(MsgRespond<List<Schedule>> result) {
                        super.onSuccess(result);
                        List<Schedule> schedules = result.getResp();
                        setData(schedules);
                    }
                });
            }
        });
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                if(!isClick){
                    return;
                }
                String date = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                ScheduleMonthView.clickDate = date;
            }
        });
    }

    private void initData(List<Schedule> schedules){

        if (schedules == null || schedules.size() == 0){
            return;
        }

        int cYear = calendarView.getCurYear();
        int cMonth = calendarView.getCurMonth();
        int cDay = calendarView.getCurDay();

        Map<String, Calendar> map = new HashMap<>();

        for(Schedule schedule : schedules){
            int year = NumberUtils.toInt(schedule.getDoc_start_time().split("-")[0],cYear);
            int month = NumberUtils.toInt(schedule.getDoc_start_time().split("-")[1],cMonth);
            int day = NumberUtils.toInt(schedule.getDoc_start_time().split(" ")[0].split("-")[2],cDay);
            int color;
            if("已完成".equals(schedule.getFd_status())){
                color = getResources().getColor(R.color.hxm_schedule_status_finish);
            }else{
                color = getResources().getColor(R.color.hxm_schedule_status_no_finish);
            }

            Calendar calendar = getSchemeCalendar(year,month,day,color,"");
            map.put(calendar.toString(), calendar);
        }

        //此方法在巨大的数据量上不影响遍历性能，推荐使用
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

    public void setData(List<Schedule> datas) {
        if(datas == null){
            this.datas.clear();
            adapter.notifyDataSetChanged();
            return;
        }

        this.datas.clear();
        this.datas.addAll(datas);
        adapter.notifyDataSetChanged();
        initData(datas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ScheduleMonthView.clickDate = "";
    }
}
