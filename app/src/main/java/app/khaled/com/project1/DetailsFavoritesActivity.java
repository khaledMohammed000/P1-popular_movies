package app.khaled.com.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsFavoritesActivity extends AppCompatActivity {
    ImageView mainImage;
    MoviesModel movieSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        movieSelected = (MoviesModel) intent.getParcelableExtra("movie");

        Log.d("object", movieSelected.toString());

        mainImage = (ImageView) findViewById(R.id.details_favorite_posterImage);
        mainImage.setScaleType(ImageView.ScaleType.FIT_XY);
        String BaseURL = "http://image.tmdb.org/t/p/w342";
        int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

        Picasso.with(getApplicationContext())
                .load(movieSelected.getPoster_path())
                .resize(width, height / 2)
                .into(mainImage);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.details_favorite_ratingBar);
        ratingBar.setRating((float) (movieSelected.getVote_average()) / 2);

        TextView title = (TextView) findViewById(R.id.details_favorite_MovieTitle);
        title.setText("Title : " + movieSelected.getTitle());

        TextView releaseDate = (TextView) findViewById(R.id.details_favorite_releaseDate);
        releaseDate.setText("Release Date : " + movieSelected.getRelease_date());

        TextView averageVote = (TextView) findViewById(R.id.details_favorite_avereageVote);
        averageVote.setText("Average Vote : " + movieSelected.getVote_average());

        TextView summary = (TextView) findViewById(R.id.details_favorite_summary);
        summary.setText("Summary : " + movieSelected.getOverview());


    }

}
