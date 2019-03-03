package ir.imandroid.moviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.adapter.MoviesAdapter;
import ir.imandroid.moviesapp.api.model.GetMovies;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.SlideInRightAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_movies)
    RecyclerView recycler_movies;
    List<GetMovies.Movie> movies;
    MoviesAdapter moviesAdapter;
    SlideInRightAnimationAdapter slideInRightAnimationAdapter;
    @BindView(R.id.edt_search)
    EditText edtSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, movies);
        recycler_movies.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));

        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(moviesAdapter);
        slideInRightAnimationAdapter = new SlideInRightAnimationAdapter(alphaInAnimationAdapter);
        slideInRightAnimationAdapter.setDuration(1000);
        slideInRightAnimationAdapter.setFirstOnly(false);
        recycler_movies.setAdapter(slideInRightAnimationAdapter);

        Call<GetMovies> req = G.service.getMovies();
        req.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                GetMovies getMovies = response.body();
                movies.addAll(getMovies.getMovies());
                slideInRightAnimationAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });


    }
}
