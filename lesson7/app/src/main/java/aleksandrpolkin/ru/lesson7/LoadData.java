package aleksandrpolkin.ru.lesson7;

import aleksandrpolkin.ru.lesson7.data.Dataset;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface LoadData {
    @GET("api/v1/bridges/?format=json")
    Single<Dataset> listData();
}
