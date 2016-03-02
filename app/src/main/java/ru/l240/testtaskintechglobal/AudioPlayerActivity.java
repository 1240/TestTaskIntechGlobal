package ru.l240.testtaskintechglobal;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import io.realm.Realm;
import ru.l240.testtaskintechglobal.models.Melody;
import ru.l240.testtaskintechglobal.realm.RealmHelper;

public class AudioPlayerActivity extends AppCompatActivity implements Runnable {

    private MediaPlayer mp;
    private SeekBar sb;
    private Melody melody;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sb", sb.getProgress());
        outState.putBoolean("isPlaying", mp.isPlaying());
        if (mp.isPlaying())
            mp.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", 0);
        setContentView(R.layout.activity_audio_player);
        melody = RealmHelper.getMelody(Realm.getInstance(this), id);
        sb = (SeekBar) findViewById(R.id.sbPlayer);
        ImageView iv = (ImageView) findViewById(R.id.ivPlayer);
        TextView name = (TextView) findViewById(R.id.tvArtistName);
        TextView title = (TextView) findViewById(R.id.tvTitle);
        name.setText(melody.getArtist());
        title.setText(melody.getTitle());
        Picasso.with(this)
                .load(melody.getPicUrl())
                .placeholder(R.mipmap.song_preview)
                .into(iv);
        if (savedInstanceState != null) {
            int sb = savedInstanceState.getInt("sb");
            boolean isPlaying = savedInstanceState.getBoolean("isPlaying");
            mp = new MediaPlayer();
            try {
                mp.setDataSource(melody.getDemoUrl());
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.prepare();
                this.sb.setMax(mp.getDuration());
                mp.seekTo(sb);
                this.sb.setProgress(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.start();
            mp.pause();
            if (isPlaying) {
                mp.start();
                new Thread(this).start();
            }

        }
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mp != null && fromUser) {
                    mp.pause();
                    mp.seekTo(progress);
                    mp.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        stop(null);
        super.onBackPressed();
        this.finish();
    }

    public void start(View view) {
        if (mp != null && mp.isPlaying()) return;
        if (sb.getProgress() > 0) {
            mp.start();
            return;
        }
        mp = new MediaPlayer();
        try {
            mp.setDataSource(melody.getDemoUrl());
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
        sb.setMax(mp.getDuration());
        new Thread(this).start();

    }

    @Override
    public void run() {
        int currentPosition = mp.getCurrentPosition();
        int total = mp.getDuration();
        while (mp != null && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mp.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            sb.setProgress(currentPosition);
        }
    }

    public void pause(View view) {
        if (mp != null && mp.isPlaying())
            mp.pause();
    }

    public void stop(View view) {
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
            mp = null;
            sb.setProgress(0);

    }
}
