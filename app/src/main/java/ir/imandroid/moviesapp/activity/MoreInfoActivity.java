package ir.imandroid.moviesapp.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
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
    @BindView(R.id.txt_title_mia)
    TextView txt_title_mia;
    @BindView(R.id.txt_country_mia)
    TextView txt_country_mia;
    @BindView(R.id.txt_genres_mia)
    TextView txt_genres_mia;
    @BindView(R.id.txt_imdb_rating_mia)
    TextView txt_imdb_rating_mia;
    @BindView(R.id.txt_year_mia)
    TextView txt_year_mia;
    @BindView(R.id.img_poster_1)
    ImageView img_poster_1;
    @BindView(R.id.img_poster_2)
    ImageView img_poster_2;
    @BindView(R.id.img_poster_3)
    ImageView img_poster_3;

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

                    GetMovies.Movie data = movieResponse.getMovies().get(position);

                    Picasso.with(MoreInfoActivity.this).load(data.getPoster()).into((img_movie_poster));

                    txt_title_mia.setText(data.getTitle());
                    txt_year_mia.setText(data.getYear());
                    txt_country_mia.setText(data.getCountry());
                    txt_imdb_rating_mia.setText(data.getImdbRating());
                    StringBuilder genres = new StringBuilder();
                    for (String genre : data.getGenres()) {
                        genres.append(genre+", ");
                    }
                    txt_genres_mia.setText(genres.substring(0,genres.length()));

                    Picasso.Builder picassoBuilder = new Picasso.Builder(MoreInfoActivity.this);
                    Picasso picasso = picassoBuilder.build();

                        picasso.load(data.getImages().get(0)).into(img_poster_1);
                        picasso.load(data.getImages().get(1)).into(img_poster_2);
                        picasso.load(data.getImages().get(2)).into(img_poster_3);



                }

            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });

    }
}
