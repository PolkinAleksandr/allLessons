package aleksandrpolkin.ru.lesson5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity5 extends AppCompatActivity {

    static final int REQUEST_CODE_FOR_ACTIVITY5 = 105;
    static final String KEY_TEXT_FOR_ACTIVITY3 = "KEY_ACTIVITY_5";

    public static Intent createStartActivity(Context context) {
        return new Intent(context, Activity5.class);
    }

    @BindView(R.id.editText_words)
    EditText editDeliver;

    @BindView(R.id.button_start_activity3)
    Button buttonDeliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        ButterKnife.bind(this);

        buttonDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.createStartActivity(Activity5.this));
                intent.putExtra(KEY_TEXT_FOR_ACTIVITY3, editDeliver.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
