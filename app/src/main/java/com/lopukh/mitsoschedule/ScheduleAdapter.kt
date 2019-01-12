package com.lopukh.mitsoschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ScheduleAdapter(val scheduleList: MutableList<DayModel>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.rw_item, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val day: DayModel = scheduleList[p1]
        p0.dayText.text = ("${day.day}, ${day.date}")

        p0.time1.text = day.time[1]
        p0.time2.text = day.time[2]
        p0.time3.text = day.time[3]
        p0.time4.text = day.time[4]
        p0.time5.text = day.time[5]
        p0.time6.text = day.time[6]
        p0.time7.text = day.time[7]
        //p0.time8.text = day.time[8]

        p0.subj1.text = day.subject[1]
        p0.subj2.text = day.subject[2]
        p0.subj3.text = day.subject[3]
        p0.subj4.text = day.subject[4]
        p0.subj5.text = day.subject[5]
        p0.subj6.text = day.subject[6]
        p0.subj7.text = day.subject[7]
        //p0.subj8.text = day.subject[8]

        p0.class1.text = day.classroom[1]
        p0.class2.text = day.classroom[2]
        p0.class3.text = day.classroom[3]
        p0.class4.text = day.classroom[4]
        p0.class5.text = day.classroom[5]
        p0.class6.text = day.classroom[6]
        p0.class7.text = day.classroom[7]
        //p0.class8.text = day.classroom[8]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayText = itemView.findViewById<TextView>(R.id.dayText)

        val time1 = itemView.findViewById<TextView>(R.id.time1)
        val subj1 = itemView.findViewById<TextView>(R.id.subj1)
        val class1 = itemView.findViewById<TextView>(R.id.class1)

        val time2 = itemView.findViewById<TextView>(R.id.time2)
        val subj2 = itemView.findViewById<TextView>(R.id.subj2)
        val class2 = itemView.findViewById<TextView>(R.id.class2)

        val time3 = itemView.findViewById<TextView>(R.id.time3)
        val subj3 = itemView.findViewById<TextView>(R.id.subj3)
        val class3 = itemView.findViewById<TextView>(R.id.class3)

        val time4 = itemView.findViewById<TextView>(R.id.time4)
        val subj4 = itemView.findViewById<TextView>(R.id.subj4)
        val class4 = itemView.findViewById<TextView>(R.id.class4)

        val time5 = itemView.findViewById<TextView>(R.id.time5)
        val subj5 = itemView.findViewById<TextView>(R.id.subj5)
        val class5 = itemView.findViewById<TextView>(R.id.class5)

        val time6 = itemView.findViewById<TextView>(R.id.time6)
        val subj6 = itemView.findViewById<TextView>(R.id.subj6)
        val class6 = itemView.findViewById<TextView>(R.id.class6)

        val time7 = itemView.findViewById<TextView>(R.id.time7)
        val subj7 = itemView.findViewById<TextView>(R.id.subj7)
        val class7 = itemView.findViewById<TextView>(R.id.class7)

        val time8 = itemView.findViewById<TextView>(R.id.time8)
        val subj8 = itemView.findViewById<TextView>(R.id.subj8)
        val class8 = itemView.findViewById<TextView>(R.id.class8)
    }
}