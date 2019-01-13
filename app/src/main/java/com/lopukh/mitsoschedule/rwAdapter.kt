package com.lopukh.mitsoschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class rwAdapter(val subjList: ArrayList<SubjModel>?) : RecyclerView.Adapter<rwAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): rwAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.rw_item, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return if (subjList != null) {
            subjList.size
        } else -1
    }

    override fun onBindViewHolder(p0: rwAdapter.ViewHolder, p1: Int) {
        p0.timeSh.text = subjList!![p1].time
        p0.subjectSh.text = subjList[p1].subject
        p0.classSh.text = subjList[p1].classRoom
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val timeSh = itemView.findViewById<TextView>(R.id.timeShow)
        val subjectSh = itemView.findViewById<TextView>(R.id.subjectShow)
        val classSh = itemView.findViewById<TextView>(R.id.classShow)
    }

}