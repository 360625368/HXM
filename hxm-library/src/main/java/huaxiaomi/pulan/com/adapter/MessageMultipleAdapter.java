package huaxiaomi.pulan.com.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.message.holder.ApprovalMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.AttendanceMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.DealtMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.MeetingMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.PendingMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.SalaryMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.SaturationMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.ScheduleMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.TextMessageViewHolder;
import huaxiaomi.pulan.com.message.holder.VoiceMessageViewHolder;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.widget.ChatLayout;

/**
 * Description:消息多视图item适配器
 * <p>
 * Author: zcc
 * Date: 2018/9/11.
 */
public class MessageMultipleAdapter extends BaseMultiItemQuickAdapter<Message,BaseViewHolder> {

    private Map<Integer,MessageMultipleHolder> holderMap = new HashMap<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MessageMultipleAdapter(List<Message> data) {
        super(data);

        holderMap.put(Message.TYPE_TEXT,new TextMessageViewHolder());
        holderMap.put(Message.TYPE_VOICE,new VoiceMessageViewHolder());
        holderMap.put(Message.TYPE_SALARY,new SalaryMessageViewHolder());
        holderMap.put(Message.TYPE_ATTENDANCE,new AttendanceMessageViewHolder());
        holderMap.put(Message.TYPE_MEETING,new MeetingMessageViewHolder());
        holderMap.put(Message.TYPE_SCHEDULE,new ScheduleMessageViewHolder());
        holderMap.put(Message.TYPE_TO_READ,new PendingMessageViewHolder());
        holderMap.put(Message.TYPE_TO_DO,new DealtMessageViewHolder());
        holderMap.put(Message.TYPE_SATURATION,new SaturationMessageViewHolder());
        holderMap.put(Message.TYPE_APPROVAL,new ApprovalMessageViewHolder());


        for(Map.Entry<Integer,MessageMultipleHolder> entry : holderMap.entrySet()){
            MessageMultipleHolder holder = entry.getValue();
            addItemType(entry.getKey(),holder.getLayout());
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {

        MessageMultipleHolder holder = holderMap.get(helper.getItemViewType());

        if(holder != null){
            holder.init(helper,item);
        }
    }

    public static abstract class MessageMultipleHolder<T extends Message>{

        public abstract int getLayout();

        public void init(BaseViewHolder helper,T message){
            ChatLayout chatLayout = helper.getView(R.id.chat_layout);
            chatLayout.setDirection(message.getWhoSpeak() == 1 ? ChatLayout.Direction.LEFT : ChatLayout.Direction.RIGHT);
            convert(helper,message);
        }

        public abstract void convert(BaseViewHolder helper,T message);

    }
}
