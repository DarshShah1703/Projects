package com.projectm.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.projectm.fragments.AllopathicFragment;
import com.projectm.fragments.AyurvedicFragment;
import com.projectm.fragments.HomeopathicFragment;

public class SubMedicineAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public SubMedicineAdapter(FragmentManager fm, Context context,int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                AyurvedicFragment ayurvedicFragment=new AyurvedicFragment();
                return ayurvedicFragment;
            case 1:
                HomeopathicFragment homeopathicFragment=new HomeopathicFragment();
                return homeopathicFragment;
            case 2:
                AllopathicFragment allopathicFragment=new AllopathicFragment();
                return allopathicFragment;
            default:
                return null;
        }
    }
}
