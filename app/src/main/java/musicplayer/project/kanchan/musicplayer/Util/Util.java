package musicplayer.project.kanchan.musicplayer.Util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Util {

    public static void showLog(String tag, String msg) {
        Log.e("ShoppingCart " + tag, msg);
    }


    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
