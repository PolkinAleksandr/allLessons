package aleksandrpolkin.ru.lesson9;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationCompat;
import android.widget.ImageView;

public class DownloadReceiver extends ResultReceiver{

    static final String ARGUMENT_URL = "url";
    static final String ARGUMENT_RECEIVER = "receiver";
    static final String ARGUMENT_PROGRESS = "arg_progress";
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    String path;

    DownloadReceiver(Handler handler, NotificationCompat.Builder builder, NotificationManager notificationManager) {
        super(handler);
        this.builder = builder;
        this.notificationManager = notificationManager;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (resultCode == MyServiceDownload.UPDATE_PROGRESS) {
            int progress = resultData.getInt(ARGUMENT_PROGRESS);
                builder.setContentText(String.valueOf(progress)+ "%");
                notificationManager.notify(MainActivity.NOTIFY_ID, builder.build());
            if (progress == 100) {
                builder.setContentText("Разархивирование");
                notificationManager.notify(MainActivity.NOTIFY_ID, builder.build());
            }
            }
            if(resultCode == MyServiceDownload.PATH_UPDATE){
           //     path = resultData.getString(ARGUMENT_PROGRESS);
                notificationManager.cancelAll();

            }
        }

    }

