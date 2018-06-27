package aleksandrpolkin.ru.hhlesson3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private Toolbar toolbar;
    private TextView textViewExit;
    private EditText editTextName;
    private EditText editTextSecondname;
    private EditText editTextEmail;
    private EditText editTextLogin;
    private EditText editTextRegion;
    private Profile profile;

    public static Intent openCreateActivity(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile = new Profile();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButton = findViewById(R.id.imageButton_petrovich);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this,"К клубу Петровичей",Toast.LENGTH_SHORT).show();
            }
        });

        textViewExit = findViewById(R.id.textView_exit);
        textViewExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this,"Выход",Toast.LENGTH_SHORT).show();
            }
        });

        editTextName = findViewById(R.id.editText_name);
        editTextSecondname = findViewById(R.id.editText_secondname);
        editTextEmail = findViewById(R.id.editText_email);
        editTextLogin = findViewById(R.id.editText_login);
        editTextRegion = findViewById(R.id.editText_region);

        editTextName.setText(profile.getName());
        editTextSecondname.setText(profile.getSecondname());
        editTextEmail.setText(profile.getEmail());
        editTextLogin.setText(profile.getLogin());
        editTextRegion.setText(profile.getRegion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pencil:
                Toast.makeText(ProfileActivity.this, "Карандаши", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

