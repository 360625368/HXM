package huaxiaomi.pulan.com;

import android.media.MediaPlayer;

import org.xutils.x;

import java.io.IOException;

/**
 * Created by chenzhijie on 2018/1/20.
 */

public class AudioManager implements MediaPlayer.OnCompletionListener {

    private MediaPlayer player;

    private AudioManager() {
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private static class SingletonHolder {
        private static AudioManager instance = new AudioManager();
    }

    public static AudioManager getInstance() {
        return AudioManager.SingletonHolder.instance;
    }

    public void play(int tts) {
        if (player != null) {
            if (player.isPlaying()) {
                stop();
            }
        }
        player = MediaPlayer.create(x.app(), tts);
        player.setOnCompletionListener(this);
        player.start();
    }

    public void play(String url) {
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
                player.stop();
            }
            try {
                player.reset();
                player.setDataSource(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        player = MediaPlayer.create(x.app(), Uri.parse(url));

        player.prepareAsync();
    }

    public void stop() {
        if (player != null) {
            player.stop();
            player.reset();
            player.release();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
    }
}
