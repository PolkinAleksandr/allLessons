package aleksandrpolkin.ru.lesson11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyCustomView myCustomView = findViewById(R.id.myView);

        List<Integer> data = new ArrayList<>();
        data.add(50);
        data.add(45);
        data.add(60);
        data.add(99);
        data.add(10);
        data.add(20);
        data.add(50);
        data.add(45);
        data.add(60);
        myCustomView.setData(data);
//        myCustomView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myCustomView.animate();
//            }
//        });
    }
}
