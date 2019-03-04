package ir.imandroid.moviesapp.activity;

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

        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter((AppCompatActivity) getActivity(),movies);
        recycler_movies.setLayoutManager(new LinearLayoutManager(getContext()));
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

        return view;

    }

}
