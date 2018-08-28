package ru.handh.mvp;

import android.app.Application;

import ru.handh.mvp.di.ApplicationComponents;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class MyApplication extends Application {

    private ApplicationComponents applicationComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponents = ApplicationComponents.getInstance(this);
    }

    public ApplicationComponents getApplicationComponents() {
        return applicationComponents;
    }
}
