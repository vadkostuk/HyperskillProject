package com.justforfan.privatfree;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Проїзд");

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TicketFragment(),"Квиток");
        adapter.addFragment(new PassFragment(),"Проїзний");
        viewPager.setAdapter(adapter);

    }

    public void NewPrivatMainActivity(View view) {
        Intent intent = new Intent(MainActivity.this,PrivatMainActivity.class);
        startActivity(intent);
    }

    static class Adapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTileList = new ArrayList<>();

        public Adapter(FragmentManager manager){
            super(manager);
        }

        public Fragment getItem(int position){
            return mFragmentList.get(position);
        }
        public int getCount(){
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String tile){
            mFragmentList.add(fragment);
            mFragmentTileList.add(tile);
        }
        public CharSequence getPageTitle(int position){
            return  mFragmentTileList.get(position);
        }
    }
}
