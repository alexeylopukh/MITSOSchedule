package com.lopukh.mitsoschedule

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import android.preference.PreferenceManager
import org.jetbrains.anko.toast
import java.lang.Exception
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    var selectSchedule: String = ""
    var selectForm: String = ""
    var selectCours: Int = -1
    var selectGroup: String = ""
    val groupList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, PagesActivity::class.java)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
//        val editor = sharedPreferences.edit()
        val saveUrl = sharedPreferences.getString("url", "unknow")
        if (saveUrl != "unknow"){
            intent.putExtra("url", saveUrl)
            startActivity(intent)
        }
        val scheduleList = arrayOf("МЭОиМ", "Магистратура", "Юридический")
        val formList = arrayOf("Дневная", "Заочная", "Заочная сокр.")
        val coursList = arrayOf("1 курс", "2 курс", "3 курс", "4 курс")

        val spinSchedule = findViewById<Spinner>(R.id.spin_schedule)
        val scheduleAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item, scheduleList)
        spinSchedule.adapter = scheduleAdapter
        spinSchedule.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectSchedule = scheduleList[position]
                upgradeGroups()
            }
        }

        val spinForm = findViewById<Spinner>(R.id.spin_form)
        val formAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item, formList)
        spinForm.adapter = formAdapter
        spinForm.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectForm = formList[position]
                upgradeGroups()
            }
        }

        val spinCours = findViewById<Spinner>(R.id.spin_cours)
        val coursAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item, coursList)
        spinCours.adapter = coursAdapter
        spinCours.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectCours = position+1
                upgradeGroups()
            }
        }

        val btnGo = findViewById<Button>(R.id.btn_go)
        btnGo.setOnClickListener {
            if (selectGroup != ""){
                val url = "https://www.mitso.by/schedule/${ConverterURL.getLink(selectForm)}/"+
                        "${ConverterURL.getLink(selectSchedule)}/$selectCours%20kurs/" +
                        "${ConverterURL.wordConvert(selectGroup)}/0"
                val editor = sharedPreferences.edit()
                editor.putString("url", url)
                editor.apply()
                intent.putExtra("url", url)
                startActivity(intent)
            }
        }
        //startActivity(Intent(this, PagesActivity::class.java))

    }

    fun upgradeGroups(){
        doAsync{
            try {
                val url = "https://www.mitso.by/schedule_update?type=group_class&kaf=Glavnaya+kafedra&form=" +
                        "${ConverterURL.getLink(selectForm)}&fak=${ConverterURL.getLink(selectSchedule)}" +
                        "&kurse=$selectCours+kurs"
                val doc: Document = Jsoup.connect(url).get()
                val options = doc.select("option")
                groupList.clear()
                for (option in 0 until options.size) {
                    groupList.add(options[option].text())
                }
            }catch (e: Exception){
                val toast = Toast.makeText(
                    applicationContext, e.toString() , Toast.LENGTH_SHORT
                )
                toast.show()
            }
            uiThread{
                val spinGroup = findViewById<Spinner>(R.id.spin_group)
                val groupAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                    this@MainActivity, android.R.layout.simple_spinner_dropdown_item, groupList)
                spinGroup.adapter = groupAdapter
                spinGroup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        selectGroup = ""
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        selectGroup = groupList[position]
                    }

                }
            }
        }
    }
}
