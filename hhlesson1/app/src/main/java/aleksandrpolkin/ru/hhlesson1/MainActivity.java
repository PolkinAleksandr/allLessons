package aleksandrpolkin.ru.hhlesson1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonActivity1;
    Button buttonActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonActivity1 = findViewById(R.id.button_activity1);
        buttonActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity1.createOpenActivity(MainActivity.this));
            }
        });

        buttonActivity2 = findViewById(R.id.button_activity2);
        buttonActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Activity2.createOpenActivity(MainActivity.this));
            }
        });
    }
}
