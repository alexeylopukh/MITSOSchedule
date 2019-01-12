package com.lopukh.mitsoschedule

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.LinearLayout
import org.jetbrains.anko.AnkoAsyncContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class MainActivity : AppCompatActivity() {
    var schedule: ArrayList<DayModel> = ArrayList()
    val URL = "https://www.mitso.by/schedule/Dnevnaya/ME%60OiM/2%20kurs/1720%20ISiT/0#sch"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, PagesActivity::class.java))

    }

    private fun showSchedule(n: Int): String{
        val builder = StringBuilder()
        builder.append(schedule[n]!!.date).append(", ").append(schedule[n]!!.day).append("\n")
        for (a in 0 until schedule[n]!!.subject.size) {
            builder.append(schedule[n]!!.time[a]).append("  ")
                .append(schedule[n]!!.subject[a]).append("  ")
                .append(schedule[n]!!.classroom[a]).append("\n")
        }

//        for (day in 0 until schedule.size) {
//            builder.append(schedule[day]!!.date).append(", ").append(schedule[day]!!.day).append("\n")
//            for (a in 0 until schedule[i]!!.subject.size) {
//                builder.append(schedule[i]!!.time[a]).append("  ")
//                    .append(schedule[i]!!.subject[a]).append("  ")
//                    .append(schedule[i]!!.classroom[a]).append("\n")
//            }
//            builder.append("\n")
//        }
        return builder.toString()
    }


}
