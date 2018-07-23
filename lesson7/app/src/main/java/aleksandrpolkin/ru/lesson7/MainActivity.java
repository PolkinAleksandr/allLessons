package aleksandrpolkin.ru.lesson7;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import aleksandrpolkin.ru.lesson7.data.Dataset;
import aleksandrpolkin.ru.lesson7.data.ObjectsData;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements OnMyGetTextForActivity{

    private List<ObjectsData> objectsData;
    private FragmentTransaction fragmentTransaction;
    private TextView textView;
    private ProgressBar progressBar;
    private Button btn;
    private DisposableSingleObserver<Dataset> disposable;
    private Example example;
    static String BASE_SITE = "http://gdemost.handh.ru/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = findViewById(R.id.textView_loading);
         progressBar = findViewById(R.id.progressBar);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        example = retrofit.create(Example.class);

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

    public interface Example{
        @GET("api/v1/bridges/?format=json")
        Single<Dataset> listData();
    }

    public DisposableSingleObserver<Dataset> load() {
        return new DisposableSingleObserver<Dataset>() {
            @Override
            public void onSuccess(Dataset dataset) {
                //ObjectsData objectsData = dataset.getObjects().get(1);
                objectsData = dataset.getObjects();
                FragmentRecycle fragmentRecycle = FragmentRecycle.createInstance(objectsData);
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentRecycle, FragmentRecycle.FRAGMENT_RECYCLE_TAG);
                fragmentTransaction.commit();
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
    public void setTextForActivity(ObjectsData objectsData, String time, int pic) {
        startActivity(ActivityDescription.createOpenActivity(MainActivity.this, objectsData, time, pic));
    }



    public void loadRetrofit(DisposableSingleObserver<Dataset> disposable){
        example.listData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}

