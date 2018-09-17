package huaxiaomi.pulan.com.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import org.xutils.x;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/4 17:27
 */
public class SystemMenuDialog extends Dialog {

    private OnMenuClickListener menuClickListener;

    public SystemMenuDialog(@NonNull Context context) {
        super(context, R.style.hxm_Dialog);
        setContentView(R.layout.hxm_dialog_system_menu);

        if(getWindow() != null){
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        }

        View personLayout = findViewById(R.id.person_layout);
        View clearLayout = findViewById(R.id.clear_layout);

        personLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuClickListener != null){
                    menuClickListener.onMenuClick(0);
                }
            }
        });
        clearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuClickListener != null){
                    menuClickListener.onMenuClick(1);
                }
            }
        });
    }

    public void setMenuClickListener(OnMenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

    public interface OnMenuClickListener{
        void onMenuClick(int index);
    }
}
