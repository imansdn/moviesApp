package ir.imandroid.moviesapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.api.model.GetMovies;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.img_poster)
    ImageView img_poster;
    @BindView(R.id.lyt_movies)
    RelativeLayout lyt_movies;
    @BindView(R.id.txt_id)
    TextView txt_id;
    @BindView(R.id.txt_title)
    TextView txt_title;

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
                    ImageView imageView = new ImageView(MainActivity.this);

                    Picasso.with(MainActivity.this).load(movie.getPoster()).into(((ImageView) findViewById(R.id.img_poster)));
                    txt_id.setText(String.valueOf(movie.getId()));
                    txt_title.setText(movie.getTitle());


                }
            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });


    }
}
