package com.warden.find.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.warden.find.Fragment.AppleFragment;
import com.warden.find.Fragment.NewsFragment;
import com.warden.find.R;
import com.warden.find.model.ItemModelOfDrawerList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private ProgressDialog progressDialog;
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private int currentFragmentId = 0;
    private long firstPressTime = 0;
    private Fragment currentFragment;

    private Unbinder unbinder;
    private List<ItemModelOfDrawerList> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        // 初始化view
        initView(savedInstanceState);


    }

    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void initView(Bundle savedInstanceState) {

        // 主页面默认添加NewsFragment
        fragmentManager = getSupportFragmentManager();

        /**
         * 防止碎片重叠
         */
        Fragment videoFragment;
        Fragment weatherFragment;
        Fragment newsFragment;
        if (savedInstanceState != null) {
            currentFragment = fragmentManager.findFragmentByTag("apple");
            newsFragment = fragmentManager.findFragmentByTag("news");
            videoFragment = fragmentManager.findFragmentByTag("video");
            weatherFragment = fragmentManager.findFragmentByTag("weather");
            fragmentManager.beginTransaction().show(currentFragment)
                    .hide(newsFragment)
                    .hide(videoFragment)
                    .hide(weatherFragment)
                    .commit();

        } else {
            currentFragment = new AppleFragment();
            newsFragment = new NewsFragment();
            //videoFragment = new VideoFragment();
            //weatherFragment = new WeatherFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_content, currentFragment, "apple")
                    //.add(R.id.fragment_content, videoFragment, "video")
                    //.add(R.id.fragment_content, weatherFragment, "weather")
                    .add(R.id.fragment_content, newsFragment, "news")
                    .show(currentFragment)
                    .hide(newsFragment)
                   // .hide(videoFragment)
                    //.hide(weatherFragment)
                    .commit();
        }

        setSupportActionBar(toolbar);
        // 这句话保证title能被修改

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("苹果");


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        items.add(new ItemModelOfDrawerList(R.mipmap.apple, "苹果"));
        items.add(new ItemModelOfDrawerList(R.mipmap.news_icon, "新闻"));
        items.add(new ItemModelOfDrawerList(R.mipmap.video_icon, "视频"));
        items.add(new ItemModelOfDrawerList(R.mipmap.weather_icon, "天气"));

        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_location:
                        switchFragment(1);
                        break;
                    case R.id.nav_call:
                        switchFragment(0);
                        break;

                }
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data delete", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;



    }

    // 根据所点列表项的下标，切换fragment
    public void switchFragment(int fragmentId) {
        mDrawerLayout.closeDrawers();
        if(currentFragmentId == fragmentId)
            return;
        currentFragmentId = fragmentId;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment toFragment = null;
        switch (fragmentId)
        {
            case 0:
                toFragment = fragmentManager.findFragmentByTag("apple");
                toolbar.setTitle("苹果");
                break;
            case 1:
                toFragment = fragmentManager.findFragmentByTag("news");
                toolbar.setTitle("新闻资讯");
                break;
            case 2:
                toFragment = fragmentManager.findFragmentByTag("video");
                toolbar.setTitle("视频");
                break;
            case 3:
                toFragment = fragmentManager.findFragmentByTag("weather");
                toolbar.setTitle("天气");
                break;
        }
        fragmentTransaction.hide(currentFragment).show(toFragment).commit();
        currentFragment = toFragment;
    }

}
