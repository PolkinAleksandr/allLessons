package aleksandrpolkin.ru.hhlesson4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    private android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private List<DatasetFull> datasetFullsList;
    private DatasetFull datasetFull;

    public final int SQUARE_VIEW = 0;
    public final int FLAT_VIEW = 1;

    public static Intent createOpenActivity(Context context) {
        Intent intent = new Intent(context, RecycleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datasetFullsList = new ArrayList<>();
        createDataset("Квитанции", "-40 080,55", R.drawable.ic_bill, true);
        createDataset("Счетчики", "Подайте показания", R.drawable.ic_counter, true);
        createDataset("Рассрочка", "Сл. платеж 25.02.2018", R.drawable.ic_installment, false);
        createDataset("Памятка жителя А101", null, R.drawable.ic_about1, false);
        createDataset("Страхование", "Полис до 12.01.2019", R.drawable.ic_insurance, false);
        createDataset("Мои заявки", null, R.drawable.ic_request, false);
        createDataset("Интернет и ТВ", "Баланс 350 \u20BD", R.drawable.ic_tv, false);
        createDataset("Домофон", "Подключен", R.drawable.ic_homephone, false);
        createDataset("Охрана", "Нет", R.drawable.ic_guard, false);
        createDataset("Контакты УК и служб", null, R.drawable.ic_uk_contacts, false);

        Collections.sort(datasetFullsList,COMPARE_BY_DESCRIPTION);
        mLayoutManager = new GridLayoutManager(RecycleActivity.this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mAdapter.getItemViewType(position)) {
                    case SQUARE_VIEW:
                        return 1;
                    case FLAT_VIEW:
                        return 2;
                    default:
                        return 2;
                }
            }
        });
        //Отступы
        RecyclerView.ItemDecoration deviderItemDecoration = new DeviderItemDecoration(10);
        mRecyclerView.addItemDecoration(deviderItemDecoration);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(datasetFullsList);
        mRecyclerView.setAdapter(mAdapter);
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
            case R.id.button_menu_toast:
                Toast.makeText(RecycleActivity.this, "Домик", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.button_menu_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(RecycleActivity.this);
                builder.setMessage(R.string.text_button_recycler)
                        .setPositiveButton(R.string.text_dialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createDataset(String name, String description, int imageViewId, boolean textWarning) {
        datasetFull = new DatasetFull();
        datasetFull.setDatasetFull(name, description, imageViewId, textWarning);
        datasetFullsList.add(datasetFull);
    }

    public static final Comparator<DatasetFull> COMPARE_BY_DESCRIPTION = new Comparator<DatasetFull>() {
        @Override
        public int compare(DatasetFull lhs, DatasetFull rhs) {
            if(lhs.getDescription() == null && rhs.getDescription() != null){
                return 1;
            }else if(lhs.getDescription() != null && rhs.getDescription() == null) {
                return -1;
            }else{return 0;}
        }
    };
}