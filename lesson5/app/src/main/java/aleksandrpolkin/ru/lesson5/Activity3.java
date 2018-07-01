package aleksandrpolkin.ru.lesson5;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity3 extends AppCompatActivity {

    public static Intent createStartActivity(Context context) {
        return new Intent(context, Activity3.class);
    }

    @BindView(R.id.button_start_activity5)
    Button buttonStartActivity5;

    @BindView(R.id.button_start_activity1)
    Button buttonStartActivity1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        ButterKnife.bind(this);
        buttonStartActivity5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(Activity5.createStartActivity(Activity3.this), Activity5.REQUEST_CODE_FOR_ACTIVITY5);
            }
        });

        buttonStartActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity1.createStartActivity(Activity3.this));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Activity5.REQUEST_CODE_FOR_ACTIVITY5) {
            Snackbar.make(findViewById(R.id.linearLayout_activity3), data.getStringExtra(Activity5.KEY_TEXT_FOR_ACTIVITY3), Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }
}
