package ir.imandroid.moviesapp.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.api.model.GetMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreInfoActivity extends AppCompatActivity {

    @BindView(R.id.img_movie_poster)
    ImageView img_movie_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        ButterKnife.bind(this);

        Call<GetMovies> req = G.service.getMovies();
        req.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                GetMovies movieResponse = response.body();

                Bundle bundle = getIntent().getExtras();
                if ( bundle!=null ) {
                    int position = bundle.getInt("position");
                }

            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });

    }
}
