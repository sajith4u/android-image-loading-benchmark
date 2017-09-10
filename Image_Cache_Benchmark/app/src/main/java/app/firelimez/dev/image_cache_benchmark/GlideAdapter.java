package app.firelimez.dev.image_cache_benchmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GlideAdapter extends BaseAdapter {
    private List<ImageLoadItem> items;
    private Context ctx;
    private LayoutInflater lInflater;

    public GlideAdapter(Context ctx, List<ImageLoadItem> items) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ImageLoadItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageLoadItem item = getItem(position);
        if (convertView == null)
            convertView = lInflater.inflate(R.layout.image_item, parent, false);
        ImageView logo = (ImageView) convertView.findViewById(R.id.image);
        Glide.with(ctx)
                .load(item.getImageUrl())
                .into(logo);
        return convertView;
    }
}
