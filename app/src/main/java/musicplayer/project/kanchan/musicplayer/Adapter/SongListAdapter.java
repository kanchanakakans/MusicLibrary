package musicplayer.project.kanchan.musicplayer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import musicplayer.project.kanchan.musicplayer.Model.SongInfo;
import musicplayer.project.kanchan.musicplayer.R;
import musicplayer.project.kanchan.musicplayer.Util.IactiononClick;
import musicplayer.project.kanchan.musicplayer.Util.Util;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<SongInfo> songlistArray;
    private IactiononClick iactiononClick;

    public SongListAdapter(Context context, ArrayList<SongInfo> songlistArray, IactiononClick iactiononClick) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.songlistArray = songlistArray;
        this.iactiononClick = iactiononClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_songlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_songname.setText(songlistArray.get(position).song_name);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iactiononClick!=null){
                    Util.showLog("CheckDurarion", String.valueOf(songlistArray.get(position).song_duration));

                    iactiononClick.OnActionClick(position);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songlistArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_song_icon;
        private TextView tv_songname;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_song_icon = (ImageView) itemView.findViewById(R.id.iv_song_icon);
            tv_songname = (TextView) itemView.findViewById(R.id.tv_songname);
            view = itemView;
        }
    }
}
