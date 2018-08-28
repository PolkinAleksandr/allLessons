package ru.handh.mvp.presentation.ui.bridgedescription;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import ru.handh.mvp.R;
import ru.handh.mvp.data.model.FinalBridge;
import ru.handh.mvp.presentation.ui.base.BaseActivity;
import ru.handh.mvp.presentation.ui.bridgeslist.BridgesAdapter;

public class BridgeDescriptionActivity extends BaseActivity implements BridgesDescriptionMvpView {

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_DATA = 1;
    private static final int VIEW_ERROR = 2;
    private static final String  EXTRA_ID = "ext_id";
    private static final int DEFAULT = 0;
    private static final String BASE_SITE = "http://gdemost.handh.ru/";

    private ViewFlipper viewFlipper, viewFlipperImage;
    private Button buttonRetry;
    private TextView textNameBridge, textTimeOpen, textDescriptionBridge;
    private ImageView imageStateBridge, imageDescriptionBridge;
    private Toolbar toolbar;

    private BridgeDescriptionPresenter bridgeDescriptionPresenter;
    private BridgesAdapter bridgesAdapter;

    public static Intent createNewIntent(Context context, int id){
        Intent intent = new Intent(context, BridgeDescriptionActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge_description);

        bridgeDescriptionPresenter = getApplicationComponents().provideBridgeDescriptionPresenter();
        bridgesAdapter = getApplicationComponents().provideBridgesAdapter();

        bridgeDescriptionPresenter.attachView( this);

        viewFlipper = findViewById(R.id.viewFlipper);
        viewFlipperImage = findViewById(R.id.image_flipper);
        buttonRetry = findViewById(R.id.buttonRetry);
        textNameBridge = findViewById(R.id.textView_name);
        textTimeOpen = findViewById(R.id.textView_time);
        textDescriptionBridge = findViewById(R.id.textView_description);
        imageStateBridge = findViewById(R.id.image_state_bridge);
        imageDescriptionBridge = findViewById(R.id.image_description_bridge);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonRetry.setOnClickListener(view -> bridgeDescriptionPresenter.getBridge(loadId()));
        bridgeDescriptionPresenter.onCreate(loadId());
        buttonRetry.setOnClickListener(view -> bridgeDescriptionPresenter.getBridge(loadId()));
    }

    @Override
    protected void onDestroy() {
        bridgeDescriptionPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showLoadingError() {
        viewFlipper.setDisplayedChild(VIEW_ERROR);
    }

    @Override
    public void showBridge(FinalBridge bridge) {
        viewFlipper.setDisplayedChild(VIEW_DATA);

        textNameBridge.setText(bridge.getName());
        textTimeOpen.setText(bridge.getTime());
        imageStateBridge.setImageResource(bridge.getPic());
        textDescriptionBridge.setText(bridge.getDescription());
        bridgeDescriptionPresenter.setPicture(Glide.with(this), BASE_SITE, bridge,R.drawable.ic_brige_late);
    }

    @Override
    public void showProgressView() {
        viewFlipper.setDisplayedChild(VIEW_LOADING);
    }

    @Override
    public void showImage(Drawable image) {
        viewFlipperImage.setDisplayedChild(VIEW_DATA);
        imageDescriptionBridge.setImageDrawable(image);

    }

    @Override
    public void showProgressImage() {
        viewFlipperImage.setDisplayedChild(VIEW_LOADING);
    }

    @Override
    public void showImageError() {
        viewFlipperImage.setDisplayedChild(VIEW_ERROR);
    }

    public int loadId() {
       return getIntent().getIntExtra(EXTRA_ID, DEFAULT);
    }
}
