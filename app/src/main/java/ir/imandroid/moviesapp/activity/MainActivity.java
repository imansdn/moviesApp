package ir.imandroid.moviesapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.adapter.MoviesAdapter;
import ir.imandroid.moviesapp.api.model.GetMovies;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_movies)
    RecyclerView recycler_movies;
    List<GetMovies.Movie> movies;
    MoviesAdapter moviesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this,movies);
        recycler_movies.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL, false));
        recycler_movies.setAdapter(moviesAdapter);

        Call<GetMovies> req = G.service.getMovies();
        req.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                GetMovies getMovies = response.body();
                movies.addAll(getMovies.getMovies());
                moviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });


    }
}
