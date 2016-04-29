package app.khaled.com.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView MoviesLandingScreen;
    final public String BaseImageURL = "http://image.tmdb.org/t/p/";
    final public String StandardSIzeOfImages = "w185";
    String MyMovieDBApiKey = BuildConfig.VERIFICATION_API_KEY;
    /*
    * write your APi key here as shown below :
    * String MyMovieDBApiKey = "Your API Key Here";
    * after doing that just uncomment this
     */
    String URL = "http://api.themoviedb.org/3/movie/popular?api_key=" + MyMovieDBApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviesLandingScreen = (GridView) findViewById(R.id.gridView);
        new HelperClass(MoviesLandingScreen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_settings, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        new HelperClass(MoviesLandingScreen);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.top_rated) {
            URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=" + MyMovieDBApiKey;
            new HelperClass(MoviesLandingScreen);
            return true;
        }
        if (id == R.id.most_popular) {
            URL = "http://api.themoviedb.org/3/movie/popular?api_key=" + MyMovieDBApiKey;
            new HelperClass(MoviesLandingScreen);
            return true;
        }
        if (id == R.id.favorite) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            DbAdapter dbAdapter = new DbAdapter(this);
            if (dbAdapter.getAllFavoriteMoviesFromDB().isEmpty()) {
                Toast.makeText(this, "Favorites list is Empty", Toast.LENGTH_SHORT).show();
            }
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class HelperClass {
        GridView mGridview;

        HelperClass(GridView gridView) {
            mGridview = gridView;
            MovieDataAsyncTask movieDataAsyncTask = new MovieDataAsyncTask(getApplicationContext(), mGridview);
            movieDataAsyncTask.execute(URL);
        }
    }
}
