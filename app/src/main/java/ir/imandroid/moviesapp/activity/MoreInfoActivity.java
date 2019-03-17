package ir.imandroid.moviesapp.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import ir.imandroid.moviesapp.R;

public class MoreInfoActivity extends AppCompatActivity {

    ImageView avatarContainerView;
    Float expandedImageSize;
    Float collapsedImageSize;
    Float activityMargin;
    Boolean valuesCalculatedAlready = false;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    Float collapsedHeight = 0f;
    Float expandedHeight = 0f;
    Float maxOffset = 0f;
    int lastOffset = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);


    }
}
