package huaxiaomi.pulan.com.message.holder;

import com.chad.library.adapter.base.BaseViewHolder;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.http.entity.Saturation;
import huaxiaomi.pulan.com.widget.SaturationLayout;

/**
 * Description:工作饱和度-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/12.
 */
public class SaturationMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<Saturation>> {
    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_saturation;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<Saturation> message) {
        SaturationLayout saturationLayout = helper.getView(R.id.saturation_layout);
        saturationLayout.setSaturation(message.getResp());
    }
}
