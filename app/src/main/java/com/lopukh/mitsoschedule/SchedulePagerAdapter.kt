package com.lopukh.mitsoschedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SchedulePagerAdapter(fragmentManager: FragmentManager, private val days: ArrayList<DayModel>):
        FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(p0: Int): Fragment {
        return ScheduleFragment.newInstance(days[p0])
    }

    override fun getCount(): Int {
        return days.size
    }

}