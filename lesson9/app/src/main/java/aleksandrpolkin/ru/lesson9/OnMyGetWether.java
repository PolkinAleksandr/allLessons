package aleksandrpolkin.ru.lesson9;

import aleksandrpolkin.ru.lesson9.data.AllData;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface OnMyGetWether {
    @GET("data/2.5/weather?q=saransk&units=metric&appid=a924f0f5b30839d1ecb95f0a6416a0c2")
    Single<AllData> listData();
    }

