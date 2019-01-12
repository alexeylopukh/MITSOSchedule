package com.lopukh.mitsoschedule

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
        val rw = findViewById<RecyclerView>(R.id.rw)
        rw.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        getRasp()
    }

    fun getRasp(){
        doAsync {
            val doc: Document = Jsoup.connect(URL).get()
            val divs = doc.select("div>div[class=rp-ras-data]")
            val days = doc.select("div>div[class=rp-ras-data2]")
            val mixRasp = doc.select("div>div[class=rp-ras-opis]")

            for (div in 0 until divs.size) {
                val date = divs[div].text()
                val day = days[div].text()
                val time = arrayOfNulls<String>(8)
                val obj = arrayOfNulls<String>(8)
                val cl = arrayOfNulls<String>(8)
                val times = mixRasp[div].select("div[class=rp-r-time]") //Массив время
                val objs = mixRasp[div].select("div[class=rp-r-op]") //массив Предметы
                val cls = mixRasp[div].select("div[class=rp-r-aud]")

                for (i in 0 until cls.size) {
                    cl[i] = cls[i].text()
                }

                for (ob in 0 until objs.size) {
                    time[ob] = times[ob].text()
                    obj[ob] = objs[ob].text()
                }
                schedule.add(DayModel(date, day, time, obj, cl))
            }
            uiThread {
                val adapter = ScheduleAdapter(schedule)
                val rw = findViewById<RecyclerView>(R.id.rw)
                rw.adapter = adapter
            }
        }
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
