package aleksandrpolkin.ru.hhlesson1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Activity1 extends AppCompatActivity {

    private SortedSet<String> nameSet;
    private Iterator<String> iterator;
    private Button buttonSave;
    private Button buttonLook;
    private EditText editText;
    private TextView textView;
    private String name;

    public static Intent createOpenActivity(Context context){
        Intent intent = new Intent(context, Activity1.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        nameSet = new TreeSet<>();

        editText = findViewById(R.id.editText_activity1);
        textView = findViewById(R.id.textView_activity1);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText.getText().toString();
                nameSet.add(name);
                editText.setText("");
                Toast.makeText(Activity1.this,"Saved",Toast.LENGTH_SHORT).show();
            }
        });

        buttonLook = findViewById(R.id.button_look);
        buttonLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                iterator = nameSet.iterator();
                while (iterator.hasNext()) {
                     textView.setText(textView.getText().toString() + iterator.next() + "\n");
                  //   Toast.makeText(Activity1.this,,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
