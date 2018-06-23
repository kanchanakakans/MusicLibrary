package musicplayer.project.kanchan.musicplayer.Module;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;

import musicplayer.project.kanchan.musicplayer.R;

public class MusicPlayerDetails extends Activity {
    private ArrayList<File>songlist;
    private int selectedpos;
    private MediaPlayer mediaPlayer;
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
        initiatingMediaPlayer();


    }

    private void initiatingMediaPlayer() {
        Uri mediapath = Uri.parse(songlist.get(selectedpos).toString());
        mediaPlayer = MediaPlayer.create(getApplicationContext(),mediapath);
        mediaPlayer.start();
    }
}
