package ir.imandroid.moviesapp.fragmnet;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.imandroid.moviesapp.G;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.adapter.MoviesAdapter;
import ir.imandroid.moviesapp.api.model.GetMovies;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.SlideInRightAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( getArguments() != null ) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    RecyclerView recycle_search;
    List<GetMovies.Movie> movies;
    List<GetMovies.Movie> moviesFilter;
    List<GetMovies.Movie> moviesDefaultList;
    MoviesAdapter moviesAdapter;
    SlideInRightAnimationAdapter slideInRightAnimationAdapter;
    EditText edt_search;
    GetMovies movieResoponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_search, container, false);

        recycle_search = view.findViewById(R.id.recycle_search);
        edt_search = view.findViewById(R.id.edt_search);

        movies = new ArrayList<>();
        moviesDefaultList = new ArrayList<>();

        moviesAdapter = new MoviesAdapter((AppCompatActivity) getActivity(),moviesDefaultList);
        recycle_search.setLayoutManager(new LinearLayoutManager(getContext()));
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(moviesAdapter);
        slideInRightAnimationAdapter = new SlideInRightAnimationAdapter(alphaInAnimationAdapter);
        slideInRightAnimationAdapter.setDuration(1000);
        slideInRightAnimationAdapter.setFirstOnly(false);
        recycle_search.setAdapter(slideInRightAnimationAdapter);

        Call<GetMovies> req = G.service.searchMovies();
        req.enqueue(new Callback<GetMovies>() {
            @Override
            public void onResponse(Call<GetMovies> call, Response<GetMovies> response) {
                movieResoponse = response.body();

                movies.addAll(movieResoponse.getMovies());

                moviesDefaultList.clear();
                moviesDefaultList.addAll(movies);
                slideInRightAnimationAdapter.notifyDataSetChanged();

                edt_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                        moviesFilter = new ArrayList<>();

                        for (int j = 0; j < movies.size(); j++)
                        {

                            if (movies.get(j).getTitle().trim().contains(editable.toString()))
                            {

                                moviesFilter.add(movies.get(j));
//
                            }else
                            {
                                Toast.makeText(getActivity(), "not exist!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        moviesDefaultList.clear();
                        moviesDefaultList.addAll(moviesFilter);
                        slideInRightAnimationAdapter.notifyDataSetChanged();

                    }
                });

            }

            @Override
            public void onFailure(Call<GetMovies> call, Throwable t) {

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if ( mListener != null ) {
            mListener.onFragmentInteraction(uri);
            Toast.makeText(getActivity(), "onButtonPressed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Toast.makeText(getActivity(), "onDetach", Toast.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
