package ru.handh.mvp.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;

import ru.handh.mvp.data.model.FinalBridge;

public class ImageProvider {

    private static final String BASE_URL = "http://gdemost.handh.ru/";

    public static void loadPhoto(Context context, FinalBridge bridge, RequestListener<Drawable> listener, boolean open) {
        StringBuilder photoUrl = new StringBuilder(BASE_URL);

        if (!open) {
            photoUrl.append(bridge.getPhotoClose());
        } else {
            photoUrl.append(bridge.getPhotoOpen());
        }

        Glide.with(context)
                .load(photoUrl.toString())
                .listener(listener)
                .submit();
    }
}
