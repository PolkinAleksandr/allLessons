package aleksandrpolkin.ru.lesson7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import aleksandrpolkin.ru.lesson7.data.Dataset;
import aleksandrpolkin.ru.lesson7.data.ObjectsData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMyGetTextForActivity{

    private List<ObjectsData> objectsData;
    private FragmentTransaction fragmentTransaction;
    private TextView textView;
    private ProgressBar progressBar;
    private Button btn;
    private DisposableSingleObserver<Dataset> disposable;
    private LoadData loadData;
    static String BASE_SITE = "http://gdemost.handh.ru/";
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = findViewById(R.id.textView_loading);
         progressBar = findViewById(R.id.progressBar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        loadData = retrofit.create(LoadData.class);

        disposable = load();
        btn = findViewById(R.id.btn);
        btn.setVisibility(View.GONE);
        btn.setOnClickListener((View v) -> {
            btn.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            disposable = load();
            loadRetrofit(disposable);
        });
        loadRetrofit(disposable);
    }

    public DisposableSingleObserver<Dataset> load() {
        return new DisposableSingleObserver<Dataset>() {
            @Override
            public void onSuccess(Dataset dataset) {
                objectsData = dataset.getObjects();
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                openFragmentRecycler();
            }

            @Override
            public void onError(Throwable e) {
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                btn.setVisibility(View.VISIBLE);
                dispose();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(check){
            openFragmentRecycler();
            item.setIcon(R.drawable.ic_baseline_map_24_px);
            check = false;}
        else{
            openFragmentMap();
            item.setIcon(R.drawable.ic_baseline_list_24_px);
            check = true; }
        return true;

    }

    @Override
    public void setTextForActivity(ObjectsData objectsData, String time, int pic, int back) {
        startActivityForResult(ActivityDescription.createOpenActivity(MainActivity.this, objectsData, time, pic, back),ActivityDescription.REQUEST_CODE_DESCRIPTION);
    }



    public void loadRetrofit(DisposableSingleObserver<Dataset> disposable){
        loadData.listData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposable);
    }

    void openFragmentRecycler(){
        FragmentRecycle fragmentRecycle = FragmentRecycle.createInstance(objectsData);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentRecycle, FragmentRecycle.FRAGMENT_RECYCLE_TAG);
        fragmentTransaction.commit();
    }

    void openFragmentMap(){
        FragmentMap fragmentMap = FragmentMap.createInstance(objectsData);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentMap, FragmentMap.FRAGMENT_MAP_TAG);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == ActivityDescription.RESULT_MAP){
            openFragmentMap();
            check = true;
        }else if(resultCode == ActivityDescription.RESULT_RESYCLER){
            openFragmentRecycler();
            check = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}

