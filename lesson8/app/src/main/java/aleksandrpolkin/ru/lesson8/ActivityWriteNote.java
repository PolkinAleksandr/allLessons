package aleksandrpolkin.ru.lesson8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import data.Notebook;

public class ActivityWriteNote extends AppCompatActivity {

    EditText editTitle;
    EditText editNote;
    static final String ARGUMENT_WRITE_NOTE_COLOR = "arg_write_note_title";
    static final String ARGUMENT_WRITE_NOTE = "arg_write_note";
    static final int REQUEST_CODE_NOTE_INSERT = 210;
    static final int REQUEST_CODE_NOTE_UPDATE = 211;
    Notebook notebook;

    static Intent createOpenActivity(Context context, Notebook notebook) {
        Intent intent = new Intent(context, ActivityWriteNote.class);
        intent.putExtra(ARGUMENT_WRITE_NOTE, notebook);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);

        Toolbar toolbar = findViewById(R.id.toolbar_note);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        editTitle = findViewById(R.id.edit_title);
        editNote = findViewById(R.id.edit_note);
        notebook = getIntent().getParcelableExtra(ARGUMENT_WRITE_NOTE);
        if (notebook != null) {
            editTitle.setText(notebook.getTitleNote());
            editNote.setText(notebook.getNote());
        }
    }

    @Override
    public void onBackPressed() {
        exitActivity();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_color) {
            int color = MyColor.DEFAULT_COLOR;
            if (notebook != null) {
                color = notebook.getColorBackground();
            }
            startActivityForResult(ActivityAlertColor.createOpenActivity(ActivityWriteNote.this, color), ActivityAlertColor.REQUEST_ALERT);

        } else {
            exitActivity();
        }
        return true;

    }

    public boolean validateNote() {
        return !editTitle.getText().toString().equals("") || !editNote.getText().toString().equals("");
    }

    public void exitActivity() {
        if (validateNote()) {
            Intent intent = new Intent();
            String title = editTitle.getText().toString();
            String note = editNote.getText().toString();
            if (notebook == null) {
                notebook = new Notebook();
            }
            notebook.setTitleNote(title);
            notebook.setNote(note);
//            if(!title.equals("")){
//            notebook.setTitleNote(title);
//            }
//            if(!note.equals("")) {
//                notebook.setNote(note);
//            }
            intent.putExtra(MainActivity.MAIN_NOTE, notebook);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(ActivityWriteNote.this, getResources().getString(R.string.validate_false), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (notebook == null) {
                notebook = new Notebook();
            }
            notebook.setColorBackground(data.getIntExtra(ARGUMENT_WRITE_NOTE_COLOR, MyColor.DEFAULT_COLOR));
        }
    }
}
