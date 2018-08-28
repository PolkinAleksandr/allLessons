package ru.handh.mvp.presentation.ui.bridgedescription;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.handh.mvp.data.model.Bridge;
import ru.handh.mvp.data.model.FinalBridge;
import ru.handh.mvp.presentation.ui.base.BasePresenter;
import ru.handh.mvp.provider.BridgesFinalCreate;
import ru.handh.mvp.provider.BridgesProvider;
import ru.handh.mvp.provider.ImageProvider;

public class BridgeDescriptionPresenter extends BasePresenter<BridgesDescriptionMvpView> {

    @NonNull
    private final BridgesProvider bridgesProvider;

    @Nullable
    private Disposable disposable;

    @Nullable
    private BridgesFinalCreate bridgesFinalCreate;

    public BridgeDescriptionPresenter(@NonNull BridgesProvider bridgesProvider, BridgesFinalCreate bridgesFinalCreate) {
        this.bridgesProvider = bridgesProvider;
        this.bridgesFinalCreate = bridgesFinalCreate;
    }

    public void onCreate(int id) {
        checkViewAttached();
        getBridge(id);
    }


    public void getBridge(int id) {
        checkViewAttached();
        getMvpView().showProgressView();
        disposable = bridgesProvider.getBridge(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Bridge bridge) ->
                                getMvpView().showBridge(bridgesFinalCreate.getFinalBridgeData(bridge))
                        ,
                        error -> {
                            error.printStackTrace();
                            getMvpView().showLoadingError();
                        });
    }

    public void setPicture(Context context, FinalBridge bridge, int check) {
        getMvpView().showProgressImage();
        if(bridge.getPic() == check){
            ImageProvider.loadPhoto(context, bridge, downloadGlide(), false);
        } else {
            ImageProvider.loadPhoto(context, bridge, downloadGlide(), true);
        }
    }

    public RequestListener<Drawable> downloadGlide() {
        return new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                e.printStackTrace();
                getMvpView().showImageError();
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                getMvpView().showImage(resource);
                return true;
            }
        };
    }

    @Override
    public void doUnsubscribe() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
