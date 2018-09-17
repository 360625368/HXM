package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.http.entity.Salary;
import huaxiaomi.pulan.com.utils.NumberUtils;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/11 14:22
 */
public class SalaryLayout extends LinearLayout {

    private PieChart pieChart;
    private TextView fixSalaryTv;

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Item,BaseViewHolder> adapter;
    private List<Item> datas = new ArrayList<>();

    private TextView yearTv;
    private TextView monthTv;

    private Salary salary;

    public SalaryLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_layout_salary,this);

        if(isInEditMode()){
            return;
        }

        pieChart = findViewById(R.id.chart);
        fixSalaryTv = findViewById(R.id.fix_salary);

        recyclerView = findViewById(R.id.rv);

        yearTv = findViewById(R.id.year);
        monthTv = findViewById(R.id.month);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        yearTv.setText(String.valueOf(year));
        monthTv.setText(String.valueOf(month));

        initPieChart();

        adapter = new BaseQuickAdapter<Item, BaseViewHolder>(R.layout.hxm_item_salary,datas) {
            @Override
            protected void convert(BaseViewHolder helper, Item item) {
                helper.setBackgroundColor(R.id.rect,Color.parseColor(item.color));
                helper.setText(R.id.title,item.title);
                helper.setText(R.id.value,item.value);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void initPieChart(){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleRadius(0);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.getLegend().setEnabled(false);
    }

    private void initData(Salary salary){
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(NumberUtils.toFloat(salary.getBonus_base(),0),""));
        entries.add(new PieEntry(NumberUtils.toFloat(salary.getAchievements_nums(),0),""));
        entries.add(new PieEntry(NumberUtils.toFloat(salary.getOther_withdrawing(),0),""));
        entries.add(new PieEntry(NumberUtils.toFloat(salary.getContribution_deductible(),0),""));
        entries.add(new PieEntry(NumberUtils.toFloat(salary.getPersonal_social(),0),""));
        entries.add(new PieEntry(NumberUtils.toFloat(salary.getCompany_social(),0),""));

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(Item item : datas){
            colors.add(Color.parseColor(item.color));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(2f);
        dataSet.setDrawIcons(false);
        dataSet.setDrawValues(false);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    public void setSalary(Salary salary) {

        if(salary == null){
            return;
        }
        this.salary = salary;

        fixSalaryTv.setText("月固定工资(元):" + salary.getFixed_salary());

        datas.clear();
        datas.add(buildeItem("#B995C3","月绩效奖金基数(元):",salary.getBonus_base()));
        datas.add(buildeItem("#9D8FC3","绩效系数:",salary.getAchievements_nums()));
        datas.add(buildeItem("#8DCCDD","其它补扣款(元)",salary.getOther_withdrawing()));
        datas.add(buildeItem("#C4BEDE","捐款抵税(元):",salary.getContribution_deductible()));
        datas.add(buildeItem("#8BADDB","个人社保(元):",salary.getPersonal_social()));
        datas.add(buildeItem("#F4A8A9","公司社保(元):",salary.getCompany_social()));

        try {
            yearTv.setText(String.valueOf(salary.getMonth().split("-")[0]));
            monthTv.setText(String.valueOf(salary.getMonth().split("-")[1]));
        }catch (Exception e){
            e.printStackTrace();
        }

        initData(salary);
        adapter.notifyDataSetChanged();
    }

    private Item buildeItem(String color,String title,String value){
        Item item = new Item();
        item.color = color;
        item.title = title;
        item.value = value;
        return item;
    }

    private class Item{
        public String color;
        public String title;
        public String value;
    }
}
