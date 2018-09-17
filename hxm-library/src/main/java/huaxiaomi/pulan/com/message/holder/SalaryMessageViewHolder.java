package huaxiaomi.pulan.com.message.holder;

import com.chad.library.adapter.base.BaseViewHolder;

import huaxiaomi.pulan.com.R;
import huaxiaomi.pulan.com.adapter.MessageMultipleAdapter;
import huaxiaomi.pulan.com.http.entity.Message;
import huaxiaomi.pulan.com.http.entity.Salary;
import huaxiaomi.pulan.com.widget.SalaryLayout;

/**
 * Description:薪资查询-消息view
 * <p>
 * Author: zcc
 * Date: 2018/9/11.
 */
public class SalaryMessageViewHolder extends MessageMultipleAdapter.MessageMultipleHolder<Message<Salary>> {

    @Override
    public int getLayout() {
        return R.layout.hxm_item_message_salary;
    }

    @Override
    public void convert(BaseViewHolder helper, Message<Salary> message) {
        SalaryLayout salaryLayout = helper.getView(R.id.salary_layout);
        salaryLayout.setSalary(message.getResp());
    }
}
