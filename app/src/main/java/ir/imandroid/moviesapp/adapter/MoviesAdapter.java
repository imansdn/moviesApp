package ir.imandroid.moviesapp.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.activity.MainActivity;
import ir.imandroid.moviesapp.api.Service;
import ir.imandroid.moviesapp.api.model.GetMovies;
import ir.imandroid.moviesapp.util.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by Golden
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>  {

    private List<GetMovies.Movie> movies;
    private AppCompatActivity context;
    Gson gson =new Gson();
    public Service service;
    public  static SharedPreferences getPrefs;
    public  static SharedPreferences.Editor e;


    public MoviesAdapter(AppCompatActivity context, List<GetMovies.Movie> movies) {
        this.context=context;
        this.movies=movies;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder   {
        View view;
        @BindView(R.id.img_poster)
        ImageView img_poster;
        @BindView(R.id.lyt_movies)
        ConstraintLayout lyt_movies;
        @BindView(R.id.txt_id)
        TextView txt_id;
        @BindView(R.id.txt_title)
        TextView txt_title;
        @BindView(R.id.txt_year)
        TextView txt_year;
        @BindView(R.id.txt_country)
        TextView txt_country;
        @BindView(R.id.txt_imdb_rating)
        TextView txt_imdb_rating;
        @BindView(R.id.txt_genres)
        TextView txt_genres;
        @BindView(R.id.recy_item_ripple)
        MaterialRippleLayout recy_item_ripple;



        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.view=itemView;


            txt_title.setSelected(true);
            txt_country.setSelected(true);
            txt_genres.setSelected(true);

            recy_item_ripple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });



//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                }
//            });



        }



    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itm_movie, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final GetMovies.Movie movie =movies.get(position);
        Picasso.with(context).load(movie.getPoster()).into((holder.img_poster));
        holder.txt_id.setText(String.valueOf(movie.getId()));
        holder.txt_title.setText(movie.getTitle());
        holder.txt_year.setText(movie.getYear());
        holder.txt_country.setText(movie.getCountry());
        holder.txt_imdb_rating.setText(movie.getImdbRating());

        StringBuilder genres = new StringBuilder();
        for (String genre : movie.getGenres()) {
            genres.append(genre+", ");
        }
        holder.txt_genres.setText(genres.substring(0,genres.length()));

    }



    @Override
    public int getItemCount() {
        return movies.size();
    }




}
