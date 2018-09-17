package huaxiaomi.pulan.com.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import huaxiaomi.pulan.com.R;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 10:44
 */
public class RecordView extends LinearLayout {

    private ImageView voiceIv;

    public RecordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hxm_view_record,this);

        if(isInEditMode()){
            return;
        }
        voiceIv = findViewById(R.id.voice);
    }

    public void setVoiceLevel(int level){
        if(level < 20){
            voiceIv.setImageResource(R.mipmap.hxm_volume_1);
        }else if(level < 40){
            voiceIv.setImageResource(R.mipmap.hxm_volume_2);
        }else if(level < 60){
            voiceIv.setImageResource(R.mipmap.hxm_volume_3);
        }else if(level < 80){
            voiceIv.setImageResource(R.mipmap.hxm_volume_4);
        }else if(level < 100){
            voiceIv.setImageResource(R.mipmap.hxm_volume_5);
        }
    }
}
