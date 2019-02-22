package ir.imandroid.moviesapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.api.model.GetMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lyt_movies)
    RelativeLayout lyt_movies;
    @BindView(R.id.txt_id)
    TextView txt_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Call<GetMovies> req = G.service.getMovies();
        req.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                GetMovies getMovies = response.body();
                for (GetMovies.Movie movie :getMovies.getMovies() ){
                    TextView textView = new TextView(MainActivity.this);
                    txt_id.setText(movie.getTitle());
//                    lyt_movies.addView(textView);


                }
            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });


    }
}
