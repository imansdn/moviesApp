package ir.imandroid.moviesapp.fragmnet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.adapter.MoviesAdapter;
import ir.imandroid.moviesapp.api.model.AddMovie;
import ir.imandroid.moviesapp.api.model.GetMovies;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.SlideInRightAnimationAdapter;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class MainPageFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
//    @BindView(R.id.recycler_movies)
    RecyclerView recycler_movies;
    List<GetMovies.Movie> movies;
    MoviesAdapter moviesAdapter;
    SlideInRightAnimationAdapter slideInRightAnimationAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ButterKnife.bind(getActivity());

        view = inflater.inflate(R.layout.fragment_main_page, container, false);
        recycler_movies = view.findViewById(R.id.recycler_movies);

        //testing:
//        final EditText edt_search = view.findViewById(R.id.edt_search);
//        edt_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                edt_search.setCursorVisible(true);
//            }
//        });

        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter((AppCompatActivity) getActivity(),movies);
        recycler_movies.setLayoutManager(new LinearLayoutManager(getContext()));
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(moviesAdapter);
        slideInRightAnimationAdapter = new SlideInRightAnimationAdapter(alphaInAnimationAdapter);
        slideInRightAnimationAdapter.setDuration(1000);
        slideInRightAnimationAdapter.setFirstOnly(false);
        recycler_movies.setAdapter(slideInRightAnimationAdapter);

        //Todo get all the pages of movies - search Endless Scrolling and RecyclerView

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


        addMovieReq("HezarPA","0","Iran","1397");
        return view;

    }
    void addMovieReq(String title,String imdb,String country, String year){
        JsonObject jsonObject =  new JsonObject();
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("imdb_id",imdb);
        jsonObject.addProperty("country",country);
        jsonObject.addProperty("year",year);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());


            Call<AddMovie> req = G.service.addMovie(body);

            req.enqueue(new Callback<AddMovie>() {
                @Override
                public void onResponse(Call<AddMovie> call, Response<AddMovie> response) {
                    AddMovie addMovie = response.body();
                    if (addMovie != null) {
                        Toast.makeText(getActivity(), addMovie.getId()+"", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<AddMovie> call, Throwable t) {

                }
            });


    }

}
