package app.khaled.com.project1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user-1 on 4/15/2016.
 */
public class MovieDataAsyncTask extends AsyncTask<String, Void, MoviesModel[]> {
    Context mContext;
    GridView mGridview;

    MovieDataAsyncTask(Context context, GridView gridView) {
        mContext = context;
        mGridview = gridView;
    }

    @Override
    protected MoviesModel[] doInBackground(String... params) {
        InputStream inputStream;
        BufferedReader bufferedReader = null;
        HttpURLConnection httpURLConnection = null;
        MoviesModel moviesModel[] = null;
        String JsonDataFromWeb = null;
        try {

            URL url = new URL(params[0]);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            Log.d("RESPONSE", "The response is : " + httpURLConnection.getResponseCode());
            if (httpURLConnection.getResponseCode() != 200) {
                return null;
            }
            inputStream = httpURLConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            //convert the input data to string
            StringBuffer buffer = new StringBuffer();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            JsonDataFromWeb = buffer.toString();
            Log.v("Json RESULT", JsonDataFromWeb);
            try {
                JSONObject movieObjectJson = new JSONObject(JsonDataFromWeb);
                JSONArray moviesArray = movieObjectJson.getJSONArray("results");
                moviesModel = new MoviesModel[moviesArray.length()];

                for (int i = 0; i < moviesArray.length(); i++) {

                    JSONObject obj = moviesArray.getJSONObject(i);
                    JSONArray ids = obj.getJSONArray("genre_ids");
                    int genre_ids[] = new int[ids.length()];

                    for (int j = 0; j < ids.length(); j++) {
                        genre_ids[j] = ids.getInt(j);
                    }//end of inner for loop

                    moviesModel[i] = new MoviesModel(
                            obj.getBoolean("adult"),
                            obj.getString("backdrop_path"),
                            genre_ids,
                            obj.getLong("id"),
                            obj.getString("original_language"),
                            obj.getString("original_title"),
                            obj.getString("overview"),
                            obj.getDouble("popularity"),
                            obj.getString("poster_path"),
                            obj.getString("release_date"),
                            obj.getString("title"),
                            obj.getBoolean("video"),
                            obj.getDouble("vote_average"),
                            obj.getInt("vote_count")
                    );
                }//end of outer for loop
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //end of try block
        } catch (IOException e) {
            Log.e("IOException", "Error", e);
            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final IOException e) {
                    Log.e("Finally Block", "Error", e);
                }
            }
        }//end of finally
        return moviesModel;
    }//end of doInBackground()


    @Override
    protected void onPostExecute(final MoviesModel[] DataFromTheWeb) {
        if (DataFromTheWeb == null) {
            Toast.makeText(mContext, "Error from the Server", Toast.LENGTH_LONG).show();
        } else {
            MoviesAdapter moviesAdapter = new MoviesAdapter(mContext, DataFromTheWeb);
            mGridview.setAdapter(moviesAdapter);
            mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("movie", DataFromTheWeb[position]);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
