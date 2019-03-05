package ir.imandroid.moviesapp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.imandroid.moviesapp.R;
import ir.imandroid.moviesapp.adapter.ViewPagerAdapter;
import ir.imandroid.moviesapp.fragmnet.MainPageFragment;
import ir.imandroid.moviesapp.fragmnet.SearchFragment;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tb)
    TabLayout tb;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new MainPageFragment(), "Home");
        viewPagerAdapter.addFragments(new SearchFragment(), "Search");

        vp.setAdapter(viewPagerAdapter);
        tb.setupWithViewPager(vp);

    }
}
