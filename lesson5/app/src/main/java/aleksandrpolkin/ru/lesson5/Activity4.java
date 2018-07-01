package aleksandrpolkin.ru.lesson5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity4 extends AppCompatActivity {

    public static long timeLong;
    static final int DEFAULT_VALUE_EXTRA = 123;
    static final String KEY_TIME_FOR_ACTIVITY4 = "KEY_TIME_FOR_ACTIVITY4";

    public static Intent createStartActivity(Context context) {
        Intent intent = new Intent(context, Activity4.class);
        return intent;

    }

    @BindView(R.id.textView_time)
    TextView textViewTime;

    @BindView(R.id.button_start_activity4)
    Button buttonStartActivity4;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        ButterKnife.bind(this);
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time);
        onDisplay();
        buttonStartActivity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Activity4.createStartActivity(Activity4.this));
                newIntent.putExtra(Activity4.KEY_TIME_FOR_ACTIVITY4, getTimeLong());
                startActivity(newIntent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        onDisplay();
        super.onNewIntent(intent);
    }

    public static long getTimeLong() {
        timeLong = System.currentTimeMillis();
        return timeLong;
    }

    public void onDisplay() {
        timeLong = getIntent().getLongExtra(Activity1.KEY_TIME_FOR_ACTIVITY4, DEFAULT_VALUE_EXTRA);
        String timeString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(timeLong));
        textViewTime.setText(timeString);
    }
}
