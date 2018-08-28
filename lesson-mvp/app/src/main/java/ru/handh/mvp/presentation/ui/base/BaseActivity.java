package ru.handh.mvp.presentation.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.handh.mvp.MyApplication;
import ru.handh.mvp.di.ApplicationComponents;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ApplicationComponents getApplicationComponents() {
        return ((MyApplication) getApplication()).getApplicationComponents();
    }
}
