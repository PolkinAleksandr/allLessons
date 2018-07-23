package aleksandrpolkin.ru.lesson7;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

public class ActivityDialog extends AppCompatActivity {

    static final String ARGUMENT_DIALOG_NAME = "arg_dial_name";
    static final String ARGUMENT_DIALOG_TIME = "arg_dial_time";
    static final int ACTIVITY_DIALOG_REQUEST = 103;
    String[] time = {"0 минут","15 минут", "30 минут", "45 минут", "60 минут"};

    static Intent createOpenActivity(Context context, String name){
        Intent intent = new Intent(context, ActivityDialog.class);
        intent.putExtra(ARGUMENT_DIALOG_NAME, name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(ARGUMENT_DIALOG_NAME));
        NumberPicker numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMaxValue(4);
        numberPicker.setMinValue(0);
        numberPicker.setValue(2);
        numberPicker.setDisplayedValues(time);
        Button buttonCancel = findViewById(R.id.button_cancel);
        Button buttonOk = findViewById(R.id.button_ok);
        Intent intent = new Intent();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setResult(RESULT_CANCELED,intent);
                finish();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int timePicker = numberPicker.getValue()*15;
                Log.d("MyTag",String.valueOf(timePicker));
                intent.putExtra(ARGUMENT_DIALOG_TIME,timePicker);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
