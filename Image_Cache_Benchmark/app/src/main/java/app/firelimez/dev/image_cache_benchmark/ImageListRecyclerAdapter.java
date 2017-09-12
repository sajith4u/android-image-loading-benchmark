package app.firelimez.dev.image_cache_benchmark;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


public class ImageListRecyclerAdapter extends RecyclerView.Adapter<ImageListRecyclerAdapter.GlideGridViewHolder> {

    private Context ctx;
    private List<ImageLoadItem> items;
    private RequestManager requestManager;

    public ImageListRecyclerAdapter(Context ctx, List<ImageLoadItem> items, RequestManager requestManager) {
        this.ctx = ctx;
        this.items = items;
        this.requestManager = requestManager;
    }

    @Override
    public GlideGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null);
        ImageListRecyclerAdapter.GlideGridViewHolder viewHolder =
                new ImageListRecyclerAdapter.GlideGridViewHolder(view);
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
         /*ImageLoadHelper.loadPicassoImage(item.getImageUrl(), logo);*/
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
