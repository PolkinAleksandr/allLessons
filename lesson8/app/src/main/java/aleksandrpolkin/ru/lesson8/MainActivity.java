package aleksandrpolkin.ru.lesson8;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import data.App;
import data.DaoBase;
import data.DatabaseNote;
import data.Notebook;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements OnMyClick {

    private RecyclerView recyclerView;
    private List<Notebook> notebooks;
    static final String MAIN_NOTE = "main_note";
    private DaoBase daoBase;
    private TextView noDataText;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noDataText = findViewById(R.id.textNoData);
        recyclerView = findViewById(R.id.recycle_view);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, ActivityWriteNote.class), ActivityWriteNote.REQUEST_CODE_NOTE_INSERT);
            }
        });
        createRecycle(notebooks);

        DatabaseNote db = App.getInstance().getDatabase();
        daoBase = db.daoBase();
        daoBase.getNotebook()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Notebook>>() {
                    @Override
                    public void accept(List<Notebook> notebooks) throws Exception {
                        createRecycle(notebooks);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Notebook notebook;
        switch (requestCode) {
            case ActivityWriteNote.REQUEST_CODE_NOTE_INSERT:
                notebook = data.getParcelableExtra(MAIN_NOTE);
                daoBase.insert(notebook);
                break;
            case ActivityWriteNote.REQUEST_CODE_NOTE_UPDATE:
                notebook = data.getParcelableExtra(MAIN_NOTE);
                daoBase.update(notebook);
            default:
                break;
        }
    }


    public void createRecycle(List<Notebook> notebooks) {
        if (notebooks != null) {
            if (notebooks.size() == 0) {
                noDataText.setVisibility(View.VISIBLE);
                noDataText.setText(getResources().getString(R.string.no_data));
            } else {
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                noDataText.setVisibility(View.GONE);
                RecyclerView.Adapter adapter = new RecycleNoteAdapter(notebooks);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void setOnMyLongClick(final Notebook notebook) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getResources().getString(R.string.alert_massage));
        builder.setPositiveButton(getResources().getText(R.string.rar), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                notebook.setRar(true);
                daoBase.update(notebook);
            }
        });
        builder.setNegativeButton(getResources().getText(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                daoBase.delete(notebook);
            }
        });
        builder.show();
    }

    @Override
    public void setOnMyClick(Notebook notebook) {
        startActivityForResult(ActivityWriteNote.createOpenActivity(MainActivity.this, notebook), ActivityWriteNote.REQUEST_CODE_NOTE_UPDATE);
    }
}