package aleksandrpolkin.ru.lesson5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity2 extends AppCompatActivity {

    public static Intent createStartActivity(Context context) {
        return new Intent(context, Activity2.class);
    }

    @BindView(R.id.button_start_activity3)
    Button buttonStartActivity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ButterKnife.bind(this);

        buttonStartActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity3.createStartActivity(Activity2.this));
            }
        });

    }
}
