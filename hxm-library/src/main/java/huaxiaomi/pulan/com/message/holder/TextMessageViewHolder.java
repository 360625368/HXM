package huaxiaomi.pulan.com.message.holder;

import com.chad.library.adapter.base.BaseViewHolder;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Message;

/**
 * Description:文本消息-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/11.
 */
public class TextMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<String>> {

    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_text;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<String> message) {

        int textColor = message.getWhoSpeak() == 0 ? R.color.hxm_black : R.color.hxm_white;

        helper.setText(R.id.text,message.getResp());
        helper.setTextColor(R.id.text,helper.itemView.getResources().getColor(textColor));
    }
}
