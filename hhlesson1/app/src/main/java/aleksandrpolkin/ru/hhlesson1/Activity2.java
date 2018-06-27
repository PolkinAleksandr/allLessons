package aleksandrpolkin.ru.hhlesson1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Activity2 extends AppCompatActivity {

    private Button buttonLook;
    private TextView textView;
    private EditText editText;
    private String[] student;
    private HashMap<Integer, String[]> nameHash;

    public static Intent createOpenActivity(Context context){
        Intent intent = new Intent(context, Activity2.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        nameHash = new HashMap<Integer, String[]>();

        textView = findViewById(R.id.textView_activity2);
        editText = findViewById(R.id.editText_activity2);

        buttonLook = findViewById(R.id.button_look2);
        buttonLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                for(Map.Entry entry :nameHash.entrySet()){
                    textView.setText(textView.getText().toString() + entry.getKey() + " " + Arrays.toString((Object[]) entry.getValue())+ "\n");
                }
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener()
        { public boolean onKey(View v, int keyCode, KeyEvent event) { // If the event is a key-down event on the "enter" button
             if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
            { // Perform action on key press
                student = (editText.getText().toString()).split(" ");
                nameHash.put((int) System.currentTimeMillis(),student);
                Toast.makeText(Activity2.this, editText.getText(), Toast.LENGTH_SHORT).show();
                editText.setText("");
                return true; }
            return false; } });

    }
}
