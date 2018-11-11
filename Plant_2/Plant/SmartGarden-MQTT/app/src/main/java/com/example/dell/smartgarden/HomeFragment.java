package com.example.dell.smartgarden;

import android.content.Context;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter adapter;



        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home, container, false);

            toolbar = view.findViewById(R.id.toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            viewPager =  view.findViewById(R.id.viewpager);
            tabLayout = view.findViewById(R.id.tabs);
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons(adapter);



        return view;
        }



    //Tob tabs
    private void setupTabIcons(TabAdapter pagerAdapter) {
        for (int i=0;i<tabLayout.getTabCount();i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) tab.setCustomView(pagerAdapter.getTabView(i));
        }
    }

    //Tob tabs
    private void setupViewPager(ViewPager viewPager) {
        adapter = new TabAdapter(getChildFragmentManager(),getContext());
        adapter.addFragment(new SensorsFragment(), "Sensors");
        adapter.addFragment(new ActuatorsFragment(), "Actuators");
        viewPager.setAdapter(adapter);


    }
    }

class TabAdapter extends FragmentPagerAdapter {
    private Context context;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private int[] tabIcons = {
            R.drawable.ic_sensor_name,
            R.drawable.ic_action_name
    };


    public  TabAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.context = context;
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView textView = v.findViewById(R.id.textView);
        textView.setText(mFragmentTitleList.get(position));
        ImageView imageView = v.findViewById(R.id.imageView);
        imageView.setImageResource(tabIcons[position]);
        return v;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}


