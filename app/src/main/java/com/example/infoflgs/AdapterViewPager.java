package com.example.infoflgs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AdapterViewPager extends FragmentPagerAdapter {

    public AdapterViewPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return SetPagerView.instance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
