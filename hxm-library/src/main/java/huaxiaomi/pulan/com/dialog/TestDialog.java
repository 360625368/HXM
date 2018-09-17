package huaxiaomi.pulan.com.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.x;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/6 13:52
 */
public class TestDialog extends Dialog {

    private TextView contentTv;
    private EditText editText;

    private String content;

    private OnSendClickListener onSendClickListener;
    public TestDialog(@NonNull Context context) {
        super(context, R.style.hxm_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hxm_dialog_test);

        contentTv = findViewById(R.id.content);
        editText = findViewById(R.id.et);

        Button sendBt = findViewById(R.id.send);
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSendClickListener == null){
                    return;
                }
                String content = editText.getText().toString();
                if(TextUtils.isEmpty(content)){
                    return;
                }
                onSendClickListener.onSendClick(content);
            }
        });
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setOnSendClickListener(OnSendClickListener onSendClickListener) {
        this.onSendClickListener = onSendClickListener;
    }

    @Override
    public void show() {
        super.show();
        contentTv.setText(content);
    }

    public interface OnSendClickListener{
        void onSendClick(String content);
    }
}
