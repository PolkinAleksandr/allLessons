package aleksandrpolkin.ru.lesson7;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import aleksandrpolkin.ru.lesson7.data.ObjectsData;

public class ActivityDescription extends AppCompatActivity {

    static final String ARGUMENT_ACTIVITY_DESCRIPTION = "arg_act_desc";
    static final String ARGUMENT_DESCRIPTION_OBJECT = "arg_desc_obj";
    static final String ARGUMENT_DESCRIPTION_TIME = "arg_desc_time";
    static final String ARGUMENT_DESCRIPTION_PIC = "arg_desc_pic";
    static final String ARGUMENT_DESCRIPTION_BACK = "arg_desc_back";
    static final int REQUEST_CODE_DESCRIPTION = 304;
    static final int DEFAULT_VALUE = 0;
    static final int RESULT_MAP = 10;
    static final int RESULT_RECYCLER = 11;
    private ObjectsData objectsData;
    private ImageView imageApp;
    private ProgressBar progressBar;
    private TextView textMemory;
    private int back;
    NotificationCompat.Builder builder;
    NotificationManager notificationManager;

    static Intent createOpenActivity(Context context, ObjectsData objectsData, String time, int pic, int back){
        Intent intent = new Intent(context, ActivityDescription.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_DESCRIPTION_OBJECT, objectsData);
        bundle.putString(ARGUMENT_DESCRIPTION_TIME, time);
        bundle.putInt(ARGUMENT_DESCRIPTION_PIC, pic);
        bundle.putInt(ARGUMENT_DESCRIPTION_BACK, back);
        intent.putExtra(ARGUMENT_ACTIVITY_DESCRIPTION, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discription);
        Bundle bundle;
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bundle = getIntent().getBundleExtra(ARGUMENT_ACTIVITY_DESCRIPTION);
        objectsData = bundle.getParcelable(ARGUMENT_DESCRIPTION_OBJECT);
        String time = bundle.getString(ARGUMENT_DESCRIPTION_TIME);
        int pic = bundle.getInt(ARGUMENT_DESCRIPTION_PIC, DEFAULT_VALUE);
        back = bundle.getInt(ARGUMENT_DESCRIPTION_BACK);
        TextView textDescription = findViewById(R.id.textView_description);
        TextView textTime = findViewById(R.id.textView_time);
        TextView textName = findViewById(R.id.textView_name);
        ImageView imageBig = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        imageApp = findViewById(R.id.app_bar_image);
        imageBig.setImageResource(pic);
        findViewById(R.id.imageView2).setVisibility(View.GONE);
        textDescription.setText(Html.fromHtml(objectsData.getDescription()));
        textTime.setText(time);
        toolbar.setTitle(objectsData.getName());
        textName.setText(objectsData.getName());
        textMemory = findViewById(R.id.textView_memory);

        textMemory.setOnClickListener(v -> startActivityForResult(ActivityDialog.createOpenActivity(ActivityDescription.this,objectsData.getName()),ActivityDialog.ACTIVITY_DIALOG_REQUEST));

        if(pic == R.drawable.ic_brige_late){
            setPicture(MainActivity.BASE_SITE + objectsData.getPhotoClose());
        } else {
           setPicture(MainActivity.BASE_SITE + objectsData.getPhotoOpen());
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            int dialogTime = data.getIntExtra(ActivityDialog.ARGUMENT_DIALOG_TIME,DEFAULT_VALUE);
            textMemory.setText("За " + String.valueOf(dialogTime) + " минут");
//            builder = new NotificationCompat.Builder(this, "chanel my");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                /* Create or update. */
//                NotificationChannel channel = new NotificationChannel("chanel my",
//                        "Channel human readable title",
//                        NotificationManager.IMPORTANCE_DEFAULT);
//                notificationManager = getSystemService(NotificationManager.class);
//                assert notificationManager != null;
//                notificationManager.createNotificationChannel(channel);
//            }
//            builder.setSmallIcon(R.mipmap.ic_launcher);
//            builder.setContentText(getResources().getString(R.string.title_notification));
//            builder.setContentText(getResources().getString(R.string.download_file));
//            builder.setOngoing(true);
        }else{
            textMemory.setText(getResources().getString(R.string.memory));
        }
    }

    public void setPicture(String site){
        Glide.with(this).load(site).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                imageApp.setImageResource(R.drawable.ic_close_black_24dp);
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                imageApp.setImageDrawable(resource);
                return true;
            }
        }).submit();
    }

    @Override
    public void onBackPressed() {
        checkParentFragment();
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkParentFragment();
    }

    void checkParentFragment(){
        if(back == OnMyGetTextForActivity.map) {
            setResult(RESULT_MAP);
        }else{
            setResult(RESULT_RECYCLER);
        }
    }
}
