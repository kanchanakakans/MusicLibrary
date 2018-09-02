package musicplayer.project.kanchan.musicplayer.Module;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import musicplayer.project.kanchan.musicplayer.Model.SongInfo;
import musicplayer.project.kanchan.musicplayer.R;
import musicplayer.project.kanchan.musicplayer.Util.Util;

public class MusicPlayerDetails extends Activity implements View.OnClickListener {
    private ArrayList<SongInfo> songlist;
    private int selectedpos;
    public static MediaPlayer mediaPlayer;
    private ImageView tv_play, tv_ff, tv_bb, tv_nexttrack, tv_prevtrack;
    private SeekBar sb_seek;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer_details);
        Intent songReceive = getIntent();
        Bundle b = songReceive.getExtras();
        songlist = (ArrayList) b.getParcelableArrayList("songlist");
        selectedpos = b.getInt("pos");
        initView();
        initiatingMediaPlayer(selectedpos);


    }

    private void initView() {
        tv_play = (ImageView) findViewById(R.id.tv_play);
        tv_bb = (ImageView) findViewById(R.id.tv_bb);
        tv_ff = (ImageView) findViewById(R.id.tv_ff);
        tv_prevtrack = (ImageView) findViewById(R.id.tv_prevtrack);
        tv_nexttrack = (ImageView) findViewById(R.id.tv_nexttrack);
        sb_seek = (SeekBar) findViewById(R.id.sb_seek);
        SetClickListener();
    }

    private void SetClickListener() {
        tv_play.setOnClickListener(this);
        tv_bb.setOnClickListener(this);
        tv_ff.setOnClickListener(this);
        tv_prevtrack.setOnClickListener(this);
        tv_nexttrack.setOnClickListener(this);


    }

    private void initiatingMediaPlayer(final int selected_position) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        Uri mediapath = Uri.parse(songlist.get(selected_position).song_path);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), mediapath);
        mediaPlayer.start();
        tv_play.setImageResource(R.drawable.icn_music_pause);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (selected_position < songlist.size()) {
                    initiatingMediaPlayer(selected_position + 1);
                } else {
                    Util.showLog("CheckStopEnd", "Media Player end");

                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_play:
                if (!mediaPlayer.isPlaying()) {
                    tv_play.setImageResource(R.drawable.icn_music_pause);
                    mediaPlayer.start();
                } else {
                    tv_play.setImageResource(R.drawable.icn_play);
                    mediaPlayer.pause();
                }
                break;
            case R.id.tv_bb:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000); //<------Play 5sec forward ----->
                break;
            case R.id.tv_ff:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                break;
            case R.id.tv_nexttrack:
                selectedpos = (selectedpos + 1) % songlist.size(); // When Last Track Played it will Return to First Track
                initiatingMediaPlayer(selectedpos);
                break;
            case R.id.tv_prevtrack:
                selectedpos = (selectedpos - 1 < 0) ? songlist.size() - 1 : selectedpos - 1;
                initiatingMediaPlayer(selectedpos);
                break;

        }
    }
}
