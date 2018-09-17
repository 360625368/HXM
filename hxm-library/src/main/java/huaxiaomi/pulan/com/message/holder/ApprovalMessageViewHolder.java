package huaxiaomi.pulan.com.message.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Approval;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.widget.ApprovalLayout;

/**
 * Description:我的审批-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/12.
 */
public class ApprovalMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<List<Approval>>> {

    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_approval;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<List<Approval>> message) {
        RecyclerView recyclerView = helper.getView(R.id.rv);

        BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = new BaseQuickAdapter<Approval, BaseViewHolder>(R.layout.hxm_item_approval,new ArrayList<Approval>()) {
                @Override
                protected void convert(BaseViewHolder helper, Approval item) {
                    ApprovalLayout approvalLayout = helper.getView(R.id.approval_layout);
                    approvalLayout.setApproval(item);
                }
            };
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(adapter);
        }

        List<Approval> datas = adapter.getData();
        datas.clear();
        datas.addAll(message.getResp());
        adapter.notifyDataSetChanged();
    }
}
