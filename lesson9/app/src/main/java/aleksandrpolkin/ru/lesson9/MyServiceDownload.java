package aleksandrpolkin.ru.lesson9;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class MyServiceDownload extends IntentService {

    public static final int UPDATE_PROGRESS = 8344;
    NotificationCompat.Builder builder;
    NotificationManagerCompat notificationManager;
    int progress = 0;
    static final int NOTIFY_ID = 202;


    public MyServiceDownload() {
        super("MyServiceDownload");
    }


    @Override
        protected void onHandleIntent(Intent intent) {
            String zipFileName = getFilesDir() + "/dog.zip";
            String zipFileLocation = getFilesDir().getPath() ;
            setNotification();
            String urlToDownload = intent.getStringExtra(DownloadReceiver.ARGUMENT_URL);
            ResultReceiver receiver = intent.getParcelableExtra(DownloadReceiver.ARGUMENT_RECEIVER);
            try {
                URL url = new URL(urlToDownload);
                URLConnection connection = url.openConnection();
                connection.connect();
                // this will be useful so that you can show a typical 0-100% progress bar
                int fileLength = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(connection.getInputStream());
                OutputStream output = new FileOutputStream(getFilesDir() + "/dog.zip");

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    Bundle resultData = new Bundle();
                    progress =(int) total * 100 / fileLength;
                    resultData.putInt(DownloadReceiver.ARGUMENT_PROGRESS ,(int) (total * 100 / fileLength));
                    receiver.send(UPDATE_PROGRESS, resultData);
                    output.write(data, 0, count);
                    if(progress % 10 == 0){
                        builder.setContentText(String.valueOf(progress)+"%");
                        notificationManager.notify(NOTIFY_ID, builder.build());
                    }
                    Log.d("MyTag",String.valueOf(progress));
                }

                output.flush();
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bundle resultData = new Bundle();
            resultData.putInt(DownloadReceiver.ARGUMENT_PROGRESS ,100);
            receiver.send(UPDATE_PROGRESS, resultData);
            builder.setContentText(String.valueOf(100)+"%");
            notificationManager.notify(NOTIFY_ID, builder.build());
            Log.d("MyTag",String.valueOf(progress));
            try {
                unzip(zipFileName, zipFileLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }

            onDestroy();
        }

   protected void unzip(String zipFileName, String zipFileLocation) throws IOException {

        int BUFFER_SIZE = 1024;
                int size;
                byte[] buffer = new byte[BUFFER_SIZE];
               builder.setContentText(getResources().getString(R.string.text_unzip_notification));
               notificationManager.notify(NOTIFY_ID, builder.build());

                try {
                    File f = new File(String.valueOf(zipFileLocation));
                    if(!f.isDirectory()) {
                        f.mkdirs();
                    }
                    ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFileName), BUFFER_SIZE));
                    try {
                        ZipEntry ze;
                        while ((ze = zin.getNextEntry()) != null) {
                            String path = zipFileLocation  +"/"+ ze.getName();

                            if (ze.isDirectory()) {
                                File unzipFile = new File(path);
                                if(!unzipFile.isDirectory()) {
                                    unzipFile.mkdirs();
                                }
                            }
                            else {
                                FileOutputStream out = new FileOutputStream(path, false);
                                BufferedOutputStream fout = new BufferedOutputStream(out, BUFFER_SIZE);
                                try {
                                    while ( (size = zin.read(buffer, 0, BUFFER_SIZE)) != -1 ) {
                                        fout.write(buffer, 0, size);
                                    }

                                    zin.closeEntry();
                                }catch (Exception e) {
                                    Log.e("Exception", "Unzip exception 1:" + e.toString());
                                }
                                finally {
                                    fout.flush();
                                    fout.close();
                                }
                            }
                        }
                    }catch (Exception e) {
                        Log.e("Exception", "Unzip exception2 :" + e.toString());
                    }
                    finally {
                        zin.close();
                    }
                }
                catch (Exception e) {
                    Log.e("Exception", "Unzip exception :" + e.toString());
                }
   }

   public void setNotification(){
       builder = new NotificationCompat.Builder(this, "chanel my");
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           /* Create or update. */
           NotificationChannel channel = new NotificationChannel("chanel my",
                   "Channel human readable title",
                   NotificationManager.IMPORTANCE_DEFAULT);
           NotificationManager notificationManager = getSystemService(NotificationManager.class);
           assert notificationManager != null;
           notificationManager.createNotificationChannel(channel);
       }
       builder.setSmallIcon(R.mipmap.ic_launcher);
       builder.setContentText(getResources().getString(R.string.title_notification));
       builder.setContentText(getResources().getString(R.string.download_file));
       builder.setOngoing(true);


       notificationManager = NotificationManagerCompat.from(this);
       notificationManager.notify(NOTIFY_ID, builder.build());

   }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationManager.cancelAll();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

