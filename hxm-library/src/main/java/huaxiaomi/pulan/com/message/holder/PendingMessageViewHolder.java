package huaxiaomi.pulan.com.message.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.http.entity.Pending;
import huaxiaomi.pulan.com.widget.PendingLayout;

/**
 * Description:待阅事项-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/12.
 */
public class PendingMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<List<Pending>>> {

    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_pending;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<List<Pending>> message) {
        RecyclerView recyclerView = helper.getView(R.id.rv);

        BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = new BaseQuickAdapter<Pending, BaseViewHolder>(R.layout.hxm_item_pending,new ArrayList<Pending>()) {
                @Override
                protected void convert(BaseViewHolder helper, Pending item) {
                    PendingLayout pendingLayout = helper.getView(R.id.pending_layout);
                    pendingLayout.setPending(item);
                }
            };
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(adapter);
        }

        List<Pending> datas = adapter.getData();
        datas.clear();
        datas.addAll(message.getResp());
        adapter.notifyDataSetChanged();
    }
}
