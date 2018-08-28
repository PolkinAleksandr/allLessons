package ru.handh.mvp.presentation.ui.bridgeslist;

import java.util.List;

import ru.handh.mvp.data.model.Bridge;
import ru.handh.mvp.data.model.FinalBridge;
import ru.handh.mvp.presentation.ui.base.MvpView;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public interface BridgesListMvpView extends MvpView {
    void showLoadingError();
    void showBridges(List<FinalBridge> bridges);
    void showProgressView();
}
