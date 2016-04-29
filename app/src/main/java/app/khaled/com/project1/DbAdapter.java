package app.khaled.com.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by user-1 on 4/25/2016.
 */
public class DbAdapter {

    DbHelper dbHelper;
    Context mContext;
    SQLiteDatabase db;
    String posterPathReturnedByQuery = null;
    String finalImageURL = null;

    final public String BaseImageURL = "http://image.tmdb.org/t/p/";
    final public String StandardSIzeOfImages = "w500";

    static final String DATABASE_NAME = "movies.db";
    static final int DATABASE_VERSION = 1;

    DbAdapter(Context context) {
        mContext = context;
        dbHelper = new DbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return db;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public boolean setFavorite(MoviesModel favoriteMovie) {
        ContentValues movieContentValues = new ContentValues();

        posterPathReturnedByQuery = favoriteMovie.getPoster_path();
        finalImageURL = BaseImageURL + StandardSIzeOfImages + posterPathReturnedByQuery;
        movieContentValues.put(dbHelper.col2, finalImageURL);
        movieContentValues.put(dbHelper.col3, favoriteMovie.getOriginal_title());
        movieContentValues.put(dbHelper.col4, favoriteMovie.getVote_average());
        movieContentValues.put(dbHelper.col5, favoriteMovie.getRelease_date());
        movieContentValues.put(dbHelper.col6, favoriteMovie.getVote_count());
        movieContentValues.put(dbHelper.col7, favoriteMovie.getOverview());

        open();
        long value = getDatabaseInstance().insert(dbHelper.Table, null, movieContentValues);
        if (value == -1f) {
            return false;
        }
        close();
        return true;
    }

    public ArrayList<MoviesModel> getAllFavoriteMoviesFromDB() {
        ArrayList<MoviesModel> list = new ArrayList<MoviesModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + dbHelper.Table;
        db = open();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        MoviesModel obj = new MoviesModel();
                        obj.setPoster_path(cursor.getString(cursor.getColumnIndex(dbHelper.col2)));
                        obj.setTitle(cursor.getString(cursor.getColumnIndex(dbHelper.col3)));
                        obj.setVote_average(cursor.getDouble(cursor.getColumnIndex(dbHelper.col4)));
                        obj.setRelease_date(cursor.getString(cursor.getColumnIndex(dbHelper.col5)));
                        obj.setVote_count(cursor.getInt(cursor.getColumnIndex(dbHelper.col6)));
                        obj.setOverview(cursor.getString(cursor.getColumnIndex(dbHelper.col7)));
                        //you could add additional columns here..
                        list.add(obj);
                    } while (cursor.moveToNext());
                }
            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }
        } finally {
            try {
                close();
            } catch (Exception ignore) {
            }
        }
        return list;
    }

    //deletes a particular Movie
    public boolean deleteMovie(String id) {
        open();
        return db.delete(dbHelper.Table, dbHelper.col1 + "=" + id, null) > 0;
    }
}
