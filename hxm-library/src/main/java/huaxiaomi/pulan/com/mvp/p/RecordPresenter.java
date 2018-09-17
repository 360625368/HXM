package huaxiaomi.pulan.com.mvp.p;

import android.text.TextUtils;

import com.baidu.speech.EventListener;
import com.baidu.speech.asr.SpeechConstant;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.google.gson.Gson;

import java.util.List;

import huaxiaomi.pulan.com.dialog.DialogHelper;
import huaxiaomi.pulan.com.entity.Record;
import huaxiaomi.pulan.com.entity.Volume;
import huaxiaomi.pulan.com.mvp.MvpFactory;
import huaxiaomi.pulan.com.mvp.i.IRecordModel;
import huaxiaomi.pulan.com.mvp.i.IRecordPresenter;
import huaxiaomi.pulan.com.mvp.v.IBaseView;
import huaxiaomi.pulan.com.mvp.v.IRecordView;
import huaxiaomi.pulan.com.utils.LogUtils;

/**
 * Description:
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 11:16
 */
public class RecordPresenter extends BasePresenter<IRecordView> implements IRecordPresenter,EventListener {

    private IRecordModel recordModel;
    private boolean recordEndFlag = false;
    private Gson gson;
    private String recordResult;

    public RecordPresenter() {
        recordModel = MvpFactory.buildeM(IRecordModel.class);
        gson = new Gson();
    }

    @Override
    public void attachView(IBaseView view) {
        super.attachView(view);
        recordModel.init(getProxyView().getContext());
        recordModel.register(this);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        recordModel.unregister(this);
    }

    @Override
    public void startRecord() {
        PermissionUtils.permission(PermissionConstants.MICROPHONE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        recordModel.startRecord();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog();
                        }
                    }
                })
                .request();
    }

    @Override
    public void stopRecord() {
        recordModel.stopRecord();
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {

        LogUtils.log("name:" + name);
        LogUtils.log("params:" + params);

        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            getProxyView().setRecordEnable(true);
        }

        if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_END)){
            recordEndFlag = true;
        }

        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL) && recordEndFlag) {
            Record record = gson.fromJson(params,Record.class);
            recordResult = record.getBest_result();
        }

        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_VOLUME)) {
            Volume volume = gson.fromJson(params,Volume.class);
            getProxyView().setVoiceLevel(volume.getVolumePercent());
        }

        if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)){
            recordEndFlag = false;
            getProxyView().setRecordEnable(false);
            if(!TextUtils.isEmpty(recordResult)){
                getProxyView().recordResult(recordResult);
            }
            recordResult = "";
        }
    }

}
