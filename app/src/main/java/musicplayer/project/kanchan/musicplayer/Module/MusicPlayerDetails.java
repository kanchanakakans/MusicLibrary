package musicplayer.project.kanchan.musicplayer.Module;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import musicplayer.project.kanchan.musicplayer.R;

public class MusicPlayerDetails extends Activity implements View.OnClickListener {
    private ArrayList<File>songlist;
    private int selectedpos;
    private MediaPlayer mediaPlayer;
    private TextView tv_play,tv_ff,tv_bb;
    private SeekBar sb_seek;
    @Override
    protected void onStart() {
        super.onStart();
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
        initiatingMediaPlayer();


    }

    private void initView() {
        tv_play = (TextView)findViewById(R.id.tv_play);
        tv_bb = (TextView)findViewById(R.id.tv_bb);
        tv_ff = (TextView)findViewById(R.id.tv_ff);
        sb_seek = (SeekBar)findViewById(R.id.sb_seek);
        SetClickListener();
    }

    private void SetClickListener() {
        tv_play.setOnClickListener(this);
        tv_bb.setOnClickListener(this);
        tv_ff.setOnClickListener(this);

    }

    private void initiatingMediaPlayer() {
        Uri mediapath = Uri.parse(songlist.get(selectedpos).toString());
        mediaPlayer = MediaPlayer.create(getApplicationContext(),mediapath);
        mediaPlayer.start();
    }

    @Override
    public void onClick(View v) {

    }
}
