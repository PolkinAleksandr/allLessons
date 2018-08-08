package aleksandrpolkin.ru.lesson9;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements ServiceCallback{

    private TextView textViewTemp;
    private MyServiceTemp myServiceTemp;
    private boolean bound = false;
    static  final String SITE_PICTURE = "https://drive.google.com/uc?authuser=0&id=1zLDzYFwd4nKO_LEhrES0OcMMmQRoOcbJ&export=download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTemp = findViewById(R.id.text_temp);
        Button buttonDownload = findViewById(R.id.button_download);

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyServiceDownload.class);
                intent.putExtra(DownloadReceiver.ARGUMENT_URL, SITE_PICTURE);
                intent.putExtra(DownloadReceiver.ARGUMENT_RECEIVER, new DownloadReceiver(new Handler()));
                startService(intent);
            }
        });

        ImageView imageViewPictures = findViewById(R.id.picture);


        FileInputStream fis = null;
        try {
            fis = new FileInputStream(String.valueOf(getFilesDir() +"/dogs/1507483945.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fis != null) {
            BufferedInputStream bis = new BufferedInputStream(fis);

            Bitmap img = BitmapFactory.decodeStream(bis);
            imageViewPictures.setImageBitmap(img);
        }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
