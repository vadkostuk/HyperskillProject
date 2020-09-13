package com.hfad.bitsandpizzas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//Связывание SectionsPagerAdapter с ViewPager
SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
//Связывание ViewPager с TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.action_create_order:
                Intent intent = new Intent(this,OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }
        public int getCount(){
            return 4;
        }
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new TopFragment();
                case 1:
                    return new PizzaFragment();
                case 2:
                    return new PastaFragment();
                case 3:
                    return  new StoresFragment();
            }
            return null;
        }

        public CharSequence getPageTitle (int position){
            switch (position){
                case 0 :
                    return getResources().getText(R.string.home_tab);
                case 1 :
                    return getResources().getText(R.string.pizza_tab);
                case 2:
                    return getResources().getText(R.string.pasta_tab);
                case 3:
                    return getResources().getText((R.string.store_tab));
            }
            return null;
        }

    }
}
