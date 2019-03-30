package ir.imandroid.moviesapp.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.List;

import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.api.model.GetMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        Call<GetMovies> req = G.service.getMovies();
        req.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                GetMovies movieResponse = response.body();

            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });

    }
}
