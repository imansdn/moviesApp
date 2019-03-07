package ir.imandroid.moviesapp.api;

import ir.imandroid.moviesapp.api.model.GetMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {

    @GET("api/v1/movies")
    Call<GetMovies> getMovies(@Query("page") String page);

    @GET("api/v1/movies")
    Call<GetMovies> getMovies();

    @GET("api/v1/movies")
    Call<GetMovies> searchMovies(@Query("q") String name, @Query("page") String page);

    @GET("api/v1/movies")
    Call<GetMovies> searchMovies();

}
