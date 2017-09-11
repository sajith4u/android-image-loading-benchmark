package app.firelimez.dev.image_cache_benchmark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;


public class GlideRecyclerAdapter extends RecyclerView.Adapter<GlideRecyclerAdapter.GlideGridViewHolder> {

    private Context ctx;
    private List<ImageLoadItem> items;
    private RequestManager requestManager;

    public GlideRecyclerAdapter(Context ctx, List<ImageLoadItem> items, RequestManager requestManager) {
        this.ctx = ctx;
        this.items = items;
        this.requestManager = requestManager;
    }

    @Override
    public GlideGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null);
        GlideRecyclerAdapter.GlideGridViewHolder viewHolder =
                new GlideRecyclerAdapter.GlideGridViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GlideGridViewHolder holder, int position) {
        ImageLoadItem imageLoadItem = items.get(position);
        String imageUrl = imageLoadItem.getImageUrl();
        RequestOptions requestOptions = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        requestManager
                .asBitmap()
                .load(imageUrl)
                .apply(requestOptions)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class GlideGridViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public GlideGridViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
