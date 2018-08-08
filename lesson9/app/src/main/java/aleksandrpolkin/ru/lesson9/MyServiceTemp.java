package aleksandrpolkin.ru.lesson9;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import aleksandrpolkin.ru.lesson9.data.AllData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyServiceTemp extends Service{

    private String temp;
    static final String BASE_SITE = "http://api.openweathermap.org/";
    private DisposableSingleObserver<AllData> disposable;
    private OnMyGetWether onMyGetWether;
    Timer timer;
    TimerTask tTask;

    private final IBinder binder = new LocalBinder();
    // Registered callbacks
    private ServiceCallback serviceCallbacks;


    // Class used for the client Binder.
    public class LocalBinder extends Binder {
        MyServiceTemp getService() {
            // Return this instance of MyService so clients can call public methods
            return MyServiceTemp.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        onMyGetWether = retrofit.create(OnMyGetWether.class);


        if (tTask != null) tTask.cancel();
        timer = new Timer();
        tTask = new TimerTask() {
            public void run() {
                disposable = load();
                loadRetrofit(disposable);
            }
        };
        timer.schedule(tTask, 0, 60000);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setCallbacks(ServiceCallback callbacks) {
        serviceCallbacks = callbacks;
    }





    public DisposableSingleObserver<AllData> load() {
        return new DisposableSingleObserver<AllData>() {
            @Override
            public void onSuccess(AllData allData) {
                temp = allData.getMain().getTemp().toString();
                if(serviceCallbacks != null){
                    serviceCallbacks.doSomething(temp);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MyLog",e.getMessage());
                dispose();
            }
        };
    }

    public void loadRetrofit(DisposableSingleObserver<AllData> disposable){
        onMyGetWether.listData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
