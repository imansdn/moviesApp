package ir.imandroid.moviesapp.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.adapter.ViewPagerAdapter;
import ir.imandroid.moviesapp.fragmnet.BaseFragment;
import ir.imandroid.moviesapp.fragmnet.GenresFragment;
import ir.imandroid.moviesapp.fragmnet.MainPageFragment;
import ir.imandroid.moviesapp.fragmnet.SearchFragment;

public class MainActivity extends AppCompatActivity implements BaseFragment.BackHandlerInterface {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tb)
    TabLayout tb;
    ViewPagerAdapter viewPagerAdapter;
    private BaseFragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new MainPageFragment(), getString(R.string.txt_tab_Home));
        viewPagerAdapter.addFragments(new SearchFragment(), getString(R.string.txt_tab_search));
        viewPagerAdapter.addFragments(new GenresFragment(), getString(R.string.txt_tab_genres));

        vp.setAdapter(viewPagerAdapter);
        tb.setupWithViewPager(vp);

    }


    @Override
    public void onBackPressed()
    {
        if(selectedFragment == null || !selectedFragment.onBackPressed()) {
            // Selected fragment did not consume the back press event.
            super.onBackPressed();
        }
    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {

        this.selectedFragment = selectedFragment;

    }
}
