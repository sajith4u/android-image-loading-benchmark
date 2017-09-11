package app.firelimez.dev.image_cache_benchmark;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import app.firelimez.dev.image_cache_benchmark.util.GetImagesHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView images;

    private GlideRecyclerAdapter glideRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GetImagesHelper getImagesHelper = new GetImagesHelper();
        GlideAdapter glideAdapter = new GlideAdapter(this, getImagesHelper.getImageLoadItems());
        // ListView items = (ListView) findViewById(R.id.items);
        //  items.setAdapter(glideAdapter);
        images = (RecyclerView) findViewById(R.id.products);
        RequestManager requestManager = Glide.with(this);
        glideRecyclerAdapter = new GlideRecyclerAdapter(this, getImagesHelper.getImageLoadItems(), requestManager);
        images.setAdapter(glideRecyclerAdapter);
        images.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
