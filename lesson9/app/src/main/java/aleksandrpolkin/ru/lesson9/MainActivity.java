package aleksandrpolkin.ru.lesson9;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements ServiceCallback{

    static final int NOTIFY_ID = 202;
    static final String SITE_PICTURE = "https://drive.google.com/uc?authuser=0&id=1zLDzYFwd4nKO_LEhrES0OcMMmQRoOcbJ&export=download";
    static final String ARGUMENT_PATH = "arg_path";
    public static final String BROADCAST_ACTION = "aleksandrpolkin.ru.lesson9";
    private TextView textViewTemp;
    private MyServiceTemp myServiceTemp;
    private boolean bound = false;
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;
    String path;
    ImageView imageViewPictures;
    BroadcastReceiver br;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewPictures = findViewById(R.id.picture);
        textViewTemp = findViewById(R.id.text_temp);
        Button buttonDownload = findViewById(R.id.button_download);

        builder = new NotificationCompat.Builder(this, "chanel my");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* Create or update. */
            NotificationChannel channel = new NotificationChannel("chanel my",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentText(getResources().getString(R.string.title_notification));
        builder.setContentText(getResources().getString(R.string.download_file));
        builder.setOngoing(true);


        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyServiceDownload.class);
                intent.putExtra(DownloadReceiver.ARGUMENT_URL, SITE_PICTURE);
                intent.putExtra(DownloadReceiver.ARGUMENT_RECEIVER, new DownloadReceiver(new Handler(),builder, notificationManager));
                startService(intent);
            }
        });

        br = new BroadcastReceiver() {
            // действия при получении сообщений
            public void onReceive(Context context, Intent intent) {
                path = intent.getStringExtra(ARGUMENT_PATH);
               setSitePicture(imageViewPictures,path);

        }
        };
        // создаем фильтр для BroadcastReceiver
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(br, intFilt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // дерегистрируем (выключаем) BroadcastReceiver
        unregisterReceiver(br);
    }


    @Override
    protected void onStart() {
        super.onStart();
        // bind to Service
        Intent intent = new Intent(this, MyServiceTemp.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from service
        if (bound) {
            myServiceTemp.setCallbacks(null); // unregister
            unbindService(serviceConnection);
            bound = false;
        }
//        if (progressDialog != null) {
//            progressDialog.dismiss ();
//            progressDialog = null;
//        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // cast the IBinder and get MyService instance
            MyServiceTemp.LocalBinder binder = (MyServiceTemp.LocalBinder) service;
            myServiceTemp= binder.getService();
            bound = true;
            myServiceTemp.setCallbacks(MainActivity.this); // register
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    @Override
    public void doSomething(String temp) {
        textViewTemp.setText(temp);
    }


    public void setSitePicture(ImageView view, String path){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fis != null) {
            BufferedInputStream bis = new BufferedInputStream(fis);

            Bitmap img = BitmapFactory.decodeStream(bis);
            view.setImageBitmap(img);
        }
    }

    public class BROADCAST_ACTION {
    }
}
