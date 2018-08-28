package ru.handh.mvp.presentation.ui.bridgedescription;

import android.graphics.drawable.Drawable;

import ru.handh.mvp.data.model.FinalBridge;
import ru.handh.mvp.presentation.ui.base.MvpView;

public interface BridgesDescriptionMvpView extends MvpView{
    void showLoadingError();
    void showBridge(FinalBridge bridge);
    void showProgressView();
    void showImage(Drawable image);
    void showProgressImage();
    void showImageError();
}
