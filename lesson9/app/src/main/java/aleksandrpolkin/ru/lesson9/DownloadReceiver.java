package aleksandrpolkin.ru.lesson9;

import android.app.Notification;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationManagerCompat;

public class DownloadReceiver extends ResultReceiver{

    static final String ARGUMENT_URL = "url";
    static final String ARGUMENT_RECEIVER = "receiver";
    static final String ARGUMENT_PROGRESS = "arg_progress";
    NotificationManagerCompat notificationManager;
    Notification.Builder builder;


    DownloadReceiver(Handler handler, Notification.Builder builder) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (resultCode == MyServiceDownload.UPDATE_PROGRESS) {
            int progress = resultData.getInt(ARGUMENT_PROGRESS);
//            progressDialog.setProgress(progress);
            if (progress == 100) {
//                progressDialog.dismiss();

            }
        }
    }
}
