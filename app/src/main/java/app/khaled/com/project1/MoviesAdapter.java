package app.khaled.com.project1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by user-1 on 4/15/2016.
 */
public class MoviesAdapter extends BaseAdapter {

    private Context mContext;
    private MoviesModel mArray[];

    String posterPathReturnedByQuery = null;
    String finalImageURL = null;

    final public String BaseImageURL = "http://image.tmdb.org/t/p/";
    final public String StandardSIzeOfImages = "w185";


    // Constructor
    public MoviesAdapter(Context c, MoviesModel array[]) {
        mContext = c;
        mArray = array;
    }


    @Override
    public int getCount() {
        return mArray.length;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }
        // http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        posterPathReturnedByQuery = mArray[position].getPoster_path();
        finalImageURL = BaseImageURL + StandardSIzeOfImages + posterPathReturnedByQuery;
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        Picasso
                .with(mContext)
                .load(finalImageURL)
                .resize(width / 2, width / 2)
                .centerCrop()
                .into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
