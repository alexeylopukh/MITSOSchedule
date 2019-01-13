package com.lopukh.mitsoschedule

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


class ScheduleFragment : Fragment(){

    var subjList: ArrayList<SubjModel>? = ArrayList()

    companion object {
        fun newInstance(day: DayModel): ScheduleFragment {
            val fragmentFirst = ScheduleFragment()
            val args = Bundle()
            args.putString("date", day.date)
            args.putString("day", day.day)
            args.putStringArray("time", day.time)
            args.putStringArray("subject", day.subject)
            args.putStringArray("classroom", day.classroom)
            fragmentFirst.arguments = args
            return fragmentFirst
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val date = arguments!!.getString("date")
        val day = arguments!!.getString("day")
        val time = arguments!!.getStringArray("time")
        val subject = arguments!!.getStringArray("subject")
        val classroom = arguments!!.getStringArray("classroom")
        for (i in 0 until time!!.size){
            val currentSubj = SubjModel(time[i], subject[i], classroom[i])
            if (currentSubj.time != null){subjList?.add(currentSubj)}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false)
        val rw = view.findViewById<RecyclerView>(R.id.rw)
        val adapter = rwAdapter(subjList)
        rw.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rw.adapter = adapter
        return view
    }
}