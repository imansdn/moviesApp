package ir.imandroid.moviesapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetMovies {

    @SerializedName("data")
    @Expose
    private List<Movie> data = null;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public List<Movie> getMovies() {
        return data;
    }

    public void setMovies(List<Movie> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }



   static public  class Movie {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("poster")
        @Expose
        private String poster;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("imdb_rating")
        @Expose
        private String imdbRating;
        @SerializedName("genres")
        @Expose
        private List<String> genres = null;
        @SerializedName("images")
        @Expose
        private List<String> images = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getImdbRating() {
            return imdbRating;
        }

        public void setImdbRating(String imdbRating) {
            this.imdbRating = imdbRating;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

    }
    static public class Metadata {

        @SerializedName("current_page")
        @Expose
        private String currentPage;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("page_count")
        @Expose
        private Integer pageCount;
        @SerializedName("total_count")
        @Expose
        private Integer totalCount;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

    }
}

