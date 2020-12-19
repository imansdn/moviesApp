package ir.imandroid.moviesapp.fragmnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.activity.MainActivity;
import ir.imandroid.moviesapp.api.model.GetMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreInfoFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Position = "position";


    // TODO: Rename and change types of parameters
    private String mParam_position;


    View view;

    public MoreInfoFragment() {
        // Required empty public constructor
    }

    public static MoreInfoFragment newInstance(String param1, String param2) {
        MoreInfoFragment fragment = new MoreInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Position, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam_position = getArguments().getString(ARG_Position);
        }
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more_info, container, false);
        ButterKnife.bind(this,view);
        Call<GetMovies> req = G.service.getMovies();
        req.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                GetMovies movieResponse = response.body();




                    GetMovies.Movie data = movieResponse.getMovies().get(Integer.valueOf(mParam_position));

                    Picasso.with(getActivity()).load(data.getPoster()).into((img_movie_poster));

                    txt_title_mia.setText(data.getTitle());
                    txt_year_mia.setText(data.getYear());
                    txt_country_mia.setText(data.getCountry());
                    txt_imdb_rating_mia.setText(data.getImdbRating());
                    StringBuilder genres = new StringBuilder();
                    for (String genre : data.getGenres()) {
                        genres.append(genre+", ");
                    }
                    txt_genres_mia.setText(genres.substring(0,genres.length()));

                    Picasso.Builder picassoBuilder = new Picasso.Builder(getActivity());
                    Picasso picasso = picassoBuilder.build();

                    picasso.load(data.getImages().get(0)).into(img_poster_1);
                    picasso.load(data.getImages().get(1)).into(img_poster_2);
                    picasso.load(data.getImages().get(2)).into(img_poster_3);





            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public String getTagText() {
        return null;
    }

    @Override
    public boolean onBackPressed() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true ;
//        getActivity().finish();

    }


}
