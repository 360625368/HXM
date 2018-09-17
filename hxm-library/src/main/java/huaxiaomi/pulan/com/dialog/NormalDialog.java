package huaxiaomi.pulan.com.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 15:14
 */
public class NormalDialog extends Dialog {

    private RecyclerView recyclerView;
    private BaseQuickAdapter<Item,BaseViewHolder> adapter;
    private List<Item> items = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public NormalDialog(@NonNull Context context) {
        super(context,R.style.hxm_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_dialog_normal);

        recyclerView = findViewById(R.id.rv);

        Button confirmBt = findViewById(R.id.confirm);
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    for(Item item : items){
                        if(item.isCheck){
                            onItemClickListener.onItemClick(item);
                            break;
                        }
                    }
                }
                dismiss();
            }
        });

        if(getWindow() != null){
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        }

        adapter = new BaseQuickAdapter<Item, BaseViewHolder>(R.layout.hxm_item_normal_dialog,items) {
            @Override
            protected void convert(BaseViewHolder helper, final Item item) {
                helper.setText(R.id.title,item.title);
                helper.setOnCheckedChangeListener(R.id.cb, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        item.isCheck = isChecked;
                    }
                });
                helper.setChecked(R.id.cb,item.isCheck);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void show() {
        super.show();
        adapter.notifyDataSetChanged();
    }

    public void setItems(List<Item> items){
        this.items.clear();
        this.items.addAll(items);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class Item{
        public String title;
        public String data;
        public boolean isCheck;
    }

    public interface OnItemClickListener{
        void onItemClick(Item item);
    }
}
