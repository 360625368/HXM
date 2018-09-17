package huaxiaomi.pulan.com.mvp.m;

import android.content.Context;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import huaxiaomi.pulan.com.mvp.i.IRecordModel;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 11:03
 */
public class RecordModel extends BaseModel implements IRecordModel {

    private EventManager asr;
    private Map<String,Object> params;

    @Override
    public void init(Context context) {
        asr = EventManagerFactory.create(context, "asr");

        params = new LinkedHashMap<>();
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, true);
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_TOUCH);
    }

    @Override
    public void register(EventListener eventListener) {
        asr.registerListener(eventListener);
    }

    @Override
    public void unregister(EventListener eventListener) {
        asr.unregisterListener(eventListener);
    }

    @Override
    public void startRecord() {
        asr.send(SpeechConstant.ASR_START, new JSONObject(params).toString(), null, 0, 0);
    }

    @Override
    public void stopRecord() {
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }
}
