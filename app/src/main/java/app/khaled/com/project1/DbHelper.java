package app.khaled.com.project1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user-1 on 4/25/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    String Table = "Favorite";
    String col1 = "ID";
    String col2 = "PosterImageURL";
    String col3 = "MovieTitle";
    String col4 = "Rating";
    String col5 = "ReleaseDate";
    String col6 = "AverageCount";
    String col7 = "Summary";

    String DATABASE_CREATE = "create table " +
            Table + "(" +
            col1 + " integer primary key autoincrement," +
            col2 + " text ," +
            col3 + " text," +
            col4 + " float," +
            col5 + " float," +
            col6 + " text," +
            col7 + " text); ";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "Favorite");
        // Create a new one.
        onCreate(db);
    }
}
