package huaxiaomi.pulan.com.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:百度语音录音音量实体类
 * -
 *
 * Author：chasen
 * Date： 2018/9/12 13:43
 */
public class Volume {

    @SerializedName("volume-percent")
    private int volumePercent;
    private int volume;

    public int getVolumePercent() {
        return volumePercent;
    }

    public int getVolume() {
        return volume;
    }
}
