package app.khaled.com.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ImageView mainImage;
    MoviesModel movieSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        movieSelected = (MoviesModel) intent.getParcelableExtra("movie");

        Log.d("object", movieSelected.toString());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Store as Favorite", Snackbar.LENGTH_LONG)
                        .setAction("Confirm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DbAdapter dbAdapter = new DbAdapter(getApplicationContext());
                                boolean value = dbAdapter.setFavorite(movieSelected);
                                if (value == true) {
                                    Toast.makeText(getApplicationContext(), "adding as favorite successful ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "adding as favorite successful ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).show();
            }
        });

        mainImage = (ImageView) findViewById(R.id.posterImage);
        mainImage.setScaleType(ImageView.ScaleType.FIT_XY);
        String BaseURL = "http://image.tmdb.org/t/p/w342";
        int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;

        Picasso.with(getApplicationContext())
                .load(BaseURL + movieSelected.getBackdrop_path())
                .resize(width, height / 2)
                .into(mainImage);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating((float) (movieSelected.getVote_average()) / 2);

        TextView title = (TextView) findViewById(R.id.MovieTitle);
        title.setText("Title : " + movieSelected.getOriginal_title());

        TextView releaseDate = (TextView) findViewById(R.id.releaseDate);
        releaseDate.setText("Release Date : " + movieSelected.getRelease_date());

        TextView averageVote = (TextView) findViewById(R.id.avereageVote);
        averageVote.setText("Average Vote : " + movieSelected.getVote_average());

        TextView summary = (TextView) findViewById(R.id.summary);
        summary.setText("Summary : " + movieSelected.getOverview());


    }

}
