package musicplayer.project.kanchan.musicplayer.Module;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import musicplayer.project.kanchan.musicplayer.R;

public class NotificationGenerator {
    public static final String NOTIFY_PREVIOUS = "musicplayer.project.kanchan.musicplayer.previous";
    public static final String NOTIFY_DELETE = "musicplayer.project.kanchan.musicplayer.delete";
    public static final String NOTIFY_PAUSE = "musicplayer.project.kanchan.musicplayer.pause";
    public static final String NOTIFY_PLAY = "musicplayer.project.kanchan.musicplayer.play";
    public static final String NOTIFY_NEXT = "musicplayer.project.kanchan.musicplayer.next";
    public static void CustomMediaNotification(Context context){
        // Creating the remote Views
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_localnotification);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notifyintent = new Intent(context,MusicPlayerDetails.class);
        notifyintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notifyintent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.icn_music_icon);
        builder.setAutoCancel(true);
        builder.setCustomBigContentView(remoteViews);
        builder.setContentTitle("Music Player ");
        builder.setContentText("TADA ");

        // Setting the Audio Action Listener
        setlistener(remoteViews,context);
    }

    private static void setlistener(RemoteViews remoteViews, Context context) {
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent next = new Intent(NOTIFY_NEXT);
        Intent play = new Intent(NOTIFY_PLAY);

        PendingIntent pPrevious = PendingIntent.getBroadcast(context, 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_previous_sng, pPrevious);


//        PendingIntent pDelete = PendingIntent.getBroadcast(context, 0, delete, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.btnDelete, pDelete);


        PendingIntent pPause = PendingIntent.getBroadcast(context, 0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_song, pPause);


        PendingIntent pNext = PendingIntent.getBroadcast(context, 0, next, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_next_song, pNext);


        PendingIntent pPlay = PendingIntent.getBroadcast(context, 0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_play_song, pPlay);
    }
}

