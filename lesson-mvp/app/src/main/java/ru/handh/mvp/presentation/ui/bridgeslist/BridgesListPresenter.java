package ru.handh.mvp.presentation.ui.bridgeslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.handh.mvp.data.model.Bridge;
import ru.handh.mvp.presentation.ui.base.BasePresenter;
import ru.handh.mvp.presentation.ui.bridgedescription.BridgeDescriptionActivity;
import ru.handh.mvp.provider.BridgesFinalCreate;
import ru.handh.mvp.provider.BridgesProvider;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class BridgesListPresenter extends BasePresenter<BridgesListMvpView> {

    @NonNull
    private final BridgesProvider bridgesProvider;

    @Nullable
    private Disposable disposable;

    @Nullable
    private BridgesFinalCreate bridgesFinalCreate;

    public BridgesListPresenter(@NonNull BridgesProvider bridgesProvider, BridgesFinalCreate bridgesFinalCreate) {
        this.bridgesProvider = bridgesProvider;
        this.bridgesFinalCreate = bridgesFinalCreate;
    }

    public void onCreate() {
        checkViewAttached();
        getBridges();
    }

    public void getBridges() {
        checkViewAttached();
        getMvpView().showProgressView();
        disposable = bridgesProvider.getBridges()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((List<Bridge> bridges) ->
                            getMvpView().showBridges(bridgesFinalCreate.getFinalBridgesData(bridges))
                        ,
                        error -> {
                            error.printStackTrace();
                            getMvpView().showLoadingError();
                        });
    }


    public void startDescriptionActivity(Context context, int id){
        context.startActivity(BridgeDescriptionActivity.createNewIntent(context,id));
    }

    @Override
    public void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }


}
