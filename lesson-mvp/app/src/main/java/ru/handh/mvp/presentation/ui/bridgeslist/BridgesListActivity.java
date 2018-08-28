package ru.handh.mvp.presentation.ui.bridgeslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ViewFlipper;

import java.util.List;

import ru.handh.mvp.R;
import ru.handh.mvp.data.model.FinalBridge;
import ru.handh.mvp.presentation.ui.base.BaseActivity;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class BridgesListActivity extends BaseActivity implements BridgesListMvpView,BridgesChoiceGetId {

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;

    private ViewFlipper viewFlipper;
    private Button buttonRetry;
    private RecyclerView recyclerView;

    private BridgesListPresenter bridgesListPresenter;
    private BridgesAdapter bridgesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridges_list);

        bridgesListPresenter = getApplicationComponents().provideBridgesListPresenter();
        bridgesAdapter = getApplicationComponents().provideBridgesAdapter();

        bridgesListPresenter.attachView(this);

        viewFlipper = findViewById(R.id.viewFlipper);
        buttonRetry = findViewById(R.id.buttonRetry);
        recyclerView = findViewById(R.id.recyclerView);

        buttonRetry.setOnClickListener(view -> bridgesListPresenter.getBridges());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bridgesAdapter);

        bridgesListPresenter.onCreate();


    }

    @Override
    protected void onDestroy() {
        bridgesListPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showLoadingError() {
        viewFlipper.setDisplayedChild(VIEW_ERROR);
    }

    @Override
    public void showBridges(List<FinalBridge> bridges) {
        viewFlipper.setDisplayedChild(VIEW_DATA);
        bridgesAdapter.setBridges(bridges);
        Log.d("MyTag", String.valueOf(bridges.size()));
    }

    @Override
    public void showProgressView() {
        viewFlipper.setDisplayedChild(VIEW_LOADING);
    }

    @Override
    public void transferId(int id) {
        bridgesListPresenter.startDescriptionActivity(this, id);
    }
}
