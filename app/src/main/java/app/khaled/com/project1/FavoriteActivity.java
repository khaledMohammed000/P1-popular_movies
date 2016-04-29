package app.khaled.com.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    ListView favoritesList;
    ArrayList<MoviesModel> ArrayfavoritesList;
    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favoritesList = (ListView) findViewById(R.id.favoritesList);
        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailsFavoritesActivity.class);
                intent.putExtra("movie", ArrayfavoritesList.get(position));
                startActivity(intent);
            }
        });
        dbAdapter = new DbAdapter(this);


        ArrayfavoritesList = dbAdapter.getAllFavoriteMoviesFromDB();
        FavoriteAdapter itemsAdapter =
                new FavoriteAdapter(this, R.layout.favorite_item, ArrayfavoritesList);

        favoritesList.setAdapter(itemsAdapter);

    }

    public class FavoriteAdapter extends ArrayAdapter<MoviesModel> {
        Context context;

        public FavoriteAdapter(Context context, int LayoutID, ArrayList<MoviesModel> movies) {
            super(context, LayoutID, movies);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            MoviesModel moviesModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.favorite_item, parent, false);
            }
            // Lookup view for data population
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewFavorite);
            // Populate the data into the template view using the data object
            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

            Picasso.with(getApplicationContext())
                    .load(moviesModel.getPoster_path())
                    .resize(width / 2, height / 3)
                    .into(imageView);

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
