package app.firelimez.dev.image_cache_benchmark.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import app.firelimez.dev.image_cache_benchmark.R;

public class ImageLoadHelper {

    private static Picasso picasso;

    public static void loadPicassoImage(String appImgUrl, ImageView imgView) {
        if (picasso == null) {
            picasso = getSingleton(imgView.getContext());
        }
        picasso.with(imgView.getContext())
                .load(appImgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imgView);
    }

    private static Picasso getSingleton(Context context) {
        Picasso picasso = new Picasso.Builder(context)
                .memoryCache(new LruCache(50 * 1024 * 1024))
                .build();
        Picasso.setSingletonInstance(picasso);
        return picasso;
    }
}
