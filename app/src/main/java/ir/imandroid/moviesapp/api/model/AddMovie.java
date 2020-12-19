package ir.imandroid.moviesapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddMovie {


    @Expose
    @SerializedName("imdb_id")
    private String imdb_id;
    @Expose
    @SerializedName("imdb_votes")
    private String imdb_votes;
    @Expose
    @SerializedName("imdb_rating")
    private String imdb_rating;
    @Expose
    @SerializedName("country")
    private String country;
    @Expose
    @SerializedName("director")
    private String director;
    @Expose
    @SerializedName("year")
    private String year;
    @Expose
    @SerializedName("poster")
    private String poster;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("id")
    private int id;

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getImdb_votes() {
        return imdb_votes;
    }

    public void setImdb_votes(String imdb_votes) {
        this.imdb_votes = imdb_votes;
    }

    public String getImdb_rating() {
        return imdb_rating;
    }

    public void setImdb_rating(String imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
