package huaxiaomi.pulan.com.message.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Dealt;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.widget.DealtLayout;

/**
 * Description:待办事项-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/12.
 */
public class DealtMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<List<Dealt>>> {

    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_dealt;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<List<Dealt>> message) {
        RecyclerView recyclerView = helper.getView(R.id.rv);

        BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = new BaseQuickAdapter<Dealt, BaseViewHolder>(R.layout.hxm_item_dealt,new ArrayList<Dealt>()) {
                @Override
                protected void convert(BaseViewHolder helper, Dealt item) {
                    DealtLayout dealtLayout = helper.getView(R.id.dealt_layout);
                    dealtLayout.setDealt(item);
                }
            };
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(adapter);
        }

        List<Dealt> datas = adapter.getData();
        datas.clear();
        datas.addAll(message.getResp());
        adapter.notifyDataSetChanged();
    }
}
