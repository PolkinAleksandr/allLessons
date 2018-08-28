package ru.handh.mvp.provider;

import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Single;
import ru.handh.mvp.data.model.Bridge;
import ru.handh.mvp.data.model.BridgeResponse;
import ru.handh.mvp.data.remote.ApiService;

public class BridgesProvider {

    @Nullable
    private final ApiService apiService;

    public BridgesProvider(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<List<Bridge>> getBridges() {
       return apiService.getBridges()
               .map(BridgeResponse::getBridges);
    }

    public Single<Bridge> getBridge(int id) {
        return apiService.getBridgeInfo(id);
    }

    //TODO написать метод получения информации о мосте

}