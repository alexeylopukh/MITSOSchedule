package com.lopukh.mitsoschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PagesActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: SchedulePagerAdapter
    var schedule: ArrayList<DayModel> = ArrayList()
    val URL = "https://www.mitso.by/schedule/Dnevnaya/ME%60OiM/2%20kurs/1720%20ISiT/0#sch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pages)
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
                val vp = findViewById<ViewPager>(R.id.vpPage)
                pagerAdapter = SchedulePagerAdapter(supportFragmentManager, schedule)
                vp.adapter = pagerAdapter
            }
        }
    }
}
