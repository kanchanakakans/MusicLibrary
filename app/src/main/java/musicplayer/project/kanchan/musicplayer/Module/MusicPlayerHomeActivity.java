package musicplayer.project.kanchan.musicplayer.Module;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import musicplayer.project.kanchan.musicplayer.Adapter.SongListAdapter;
import musicplayer.project.kanchan.musicplayer.Model.SongInfo;
import musicplayer.project.kanchan.musicplayer.R;
import musicplayer.project.kanchan.musicplayer.Util.IactiononClick;
import musicplayer.project.kanchan.musicplayer.Util.Util;

public class MusicPlayerHomeActivity extends Activity implements IactiononClick {
    private RecyclerView rv_songlist;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<File> songlist;
    private ArrayList<SongInfo>songInfoList;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayerhome);
        //songlist = FindSongByRead(Environment.getRootDirectory());
      //  SetExternalStorageActivity();
        initLayout();
        initView();
        setAdapter();
    }

    private void SetExternalStorageActivity() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        {
            Util.showLog("Path", String.valueOf(Environment.getExternalStorageDirectory().getAbsoluteFile()));
            ///mounted
            songlist= FindSongByRead(android.os.Environment.getExternalStorageDirectory().getAbsoluteFile());
        }else{
            Util.showToast(this,"SD card not mounted");
        }
    }

    private ArrayList<File> FindSongByRead(File root){
        ArrayList<File>SongList = new ArrayList<File>();
        File[]files = root.listFiles();
        for(File singlefile : files){
            if(singlefile.isDirectory() && !singlefile.isHidden()){
                SongList.addAll(FindSongByRead(singlefile)); //<--- Recursive Call--->>
            }else{
                if(singlefile.getName().endsWith(".mp3")){
                    SongList.add(singlefile);
                }
            }
        }
        return SongList;
    }

    private void initView() {
        rv_songlist = (RecyclerView)findViewById(R.id.rv_songlist);

    }

    public void setAdapter(){
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        SongListAdapter songListAdapter = new SongListAdapter(this,songInfoList,this);
        rv_songlist.setAdapter(songListAdapter);
        rv_songlist.setLayoutManager(layoutManager);
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_recyclrview);
        rv_songlist.addItemDecoration(new musicplayer.project.kanchan.musicplayer.View.DividerItemDecoration(dividerDrawable));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void OnActionClick(int position) {
        Intent songdetails = new Intent(this,MusicPlayerDetails.class)
                .putExtra("pos",position)
                .putExtra("songlist",songInfoList);
        startActivity(songdetails);

    }
    public static File getExternalStorageDirectory() {
        return EXTERNAL_STORAGE_DIRECTORY;
    }
    private static final File EXTERNAL_STORAGE_DIRECTORY
            = getDirectory("EXTERNAL_STORAGE", "/sdcard");

    static File getDirectory(String variableName, String defaultPath) {
        String path = System.getenv(variableName);
        return path == null ? new File(defaultPath) : new File(path);
    }

    //<-----Geeting Sd Card Song Using MediaPLayer of default android ----->>
    private void initLayout() {
        songInfoList = new ArrayList<SongInfo>();
        final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final String[] cursor_cols = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION};
        final String where = MediaStore.Audio.Media.IS_MUSIC + "=1";
        final Cursor cursor = getApplicationContext().getContentResolver().query(uri,
                cursor_cols, where, null, null);

        while (cursor.moveToNext()) {
            String artist = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            String album = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            String track = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String data = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            Long albumId = cursor.getLong(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));

            int duration = cursor.getInt(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));


      /*  Uri sArtworkUri = Uri
                .parse("content://media/external/audio/albumart");
        Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

        //Logger.debug(albumArtUri.toString());
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    mContext.getContentResolver(), albumArtUri);
            bitmap = Bitmap.createScaledBitmap(bitmap, 30, 30, true);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.icn_audio);
        } catch (IOException e) {

            e.printStackTrace();
        }*/

            SongInfo songInfo = new SongInfo();
            songInfo.song_artist = artist;
            songInfo.song_name = track;
            songInfo.song_path = data;
            songInfo.song_duration = duration;
            songInfo.song_id = albumId;
            // songInfo.song_image =  getCoverArtPath(albumId,mContext);

       /* audioListModel.setArtist(artist);
        audioListModel.setBitmap(bitmap);
        audioListModel.setAlbum(album);
        audioListModel.setTrack(track);
        audioListModel.setData(data);
        audioListModel.setAlbumId(albumId);
        audioListModel.setDuration(duration);
        audioListModel.setAlbumArtUri(albumArtUri);*/

            songInfoList.add(songInfo);

        }

    }


}
