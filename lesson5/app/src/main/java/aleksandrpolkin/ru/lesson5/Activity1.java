package aleksandrpolkin.ru.lesson5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity1 extends AppCompatActivity {

    private long time;
    static final String KEY_TIME_FOR_ACTIVITY4 = "KEY_TIME_FOR_ACTIVITY4";

    public static Intent createStartActivity(Context context) {
        return new Intent(context, Activity1.class);
    }

    @BindView(R.id.button_start_activity2)
    Button buttonStartActivity2;

    @BindView(R.id.button_start_activity4)
    Button buttonStartActivity4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        ButterKnife.bind(this);

        buttonStartActivity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time =  getTime();
                Intent intent = Activity4.createStartActivity(Activity1.this);
                intent.putExtra(KEY_TIME_FOR_ACTIVITY4, time);
                startActivity(intent);
            }
        });

        buttonStartActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity2.createStartActivity(Activity1.this));
            }
        });
    }

    public static long getTime() {
        return  System.currentTimeMillis();
    }
}
