package app.khaled.com.project1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user-1 on 4/15/2016.
 */
public class MoviesModel implements Parcelable {

    String poster_path;//use
    String overview;//use
    boolean adult;
    String release_date;//use
    int genre_ids[];//use
    long id;
    String original_title;//use
    String original_language;//use
    String title;//use
    String backdrop_path;//use
    double popularity;//use
    int vote_count;//use
    boolean video;
    double vote_average;//use


    public MoviesModel(boolean adult, String backdrop_path, int[] genre_ids, long id, String original_language, String original_title, String overview, double popularity, String poster_path, String release_date, String title, boolean video, double vote_average, int vote_count) {

        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public MoviesModel(String poster_path, String original_title, double vote_average, String release_date, int vote_count, String overview) {
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.overview = overview;
    }

    public MoviesModel() {
    }


    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    // Parcelling part
    public MoviesModel(Parcel in) {
        this.poster_path = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.release_date = in.readString();
        this.genre_ids = in.createIntArray();
        this.id = in.readLong();
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = in.readDouble();
        this.vote_count = in.readInt();
        this.video = in.readByte() != 0;
        this.vote_average = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPoster_path());
        dest.writeByte((byte) (isAdult() ? 1 : 0));
        dest.writeString(getOverview());
        dest.writeString(getRelease_date());
        dest.writeIntArray(getGenre_ids());
        dest.writeLong(getId());
        dest.writeString(getOriginal_title());
        dest.writeString(getOriginal_language());
        dest.writeString(getTitle());
        dest.writeString(getBackdrop_path());
        dest.writeDouble(getPopularity());
        dest.writeInt(getVote_count());
        dest.writeByte((byte) (isVideo() ? 1 : 0));
        dest.writeDouble(getVote_average());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel(in);
        }

        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };

}
