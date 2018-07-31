package aleksandrpolkin.ru.lesson8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ActivityAlertColor extends AppCompatActivity implements OnMyColorClick {

    static final int DEFAULT_INT = 0;
    static final int REQUEST_ALERT = 401;
    static final String ARGUMENT_ALERT_COLOR = "arg_color";
    static final int SIZE_SPAN_RECYCLE = 4;


    static Intent createOpenActivity(Context context, int color) {
        Intent intent = new Intent(context, ActivityAlertColor.class);
        intent.putExtra(ARGUMENT_ALERT_COLOR, color);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar_alert_color);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_alert_color);
        int color = getIntent().getIntExtra(ARGUMENT_ALERT_COLOR, DEFAULT_INT);
        RecyclerView recyclerView = findViewById(R.id.recycle_view_color);
        GridLayoutManager layoutManager = new GridLayoutManager(ActivityAlertColor.this, SIZE_SPAN_RECYCLE);
        recyclerView.setLayoutManager(layoutManager);
        List<Integer> myColorList = new ArrayList<>();
        RecyclerView.Adapter adapter = new RecyclerColorAdapter(setColorList(myColorList), color);
        recyclerView.setAdapter(adapter);
        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private List<Integer> setColorList(@NonNull List<Integer> colorList) {
        MyColor myColor = new MyColor();
        for (int i = 0; i <= MyColor.COLOR_SIZE; i++) {
            colorList.add(myColor.getMyColor(i));
            Log.d("MyTag", String.valueOf(myColor.getMyColor(i)));
        }
        return colorList;
    }

    @Override
    public void setOnMyClick(int color) {
        Intent intent = new Intent();
        intent.putExtra(ActivityWriteNote.ARGUMENT_WRITE_NOTE_COLOR, color);
        setResult(RESULT_OK, intent);
        finish();
    }
}
