package com.lopukh.mitsoschedule

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ScheduleFragment : Fragment(){

//    private var page: Int? = null
    private var date: String? = null
    private var day: String? = null
    var time: Array<String>? = null
    var subject: Array<String>? = null
    var classroom: Array<String?>? = null

    companion object {
        fun newInstance(day: DayModel): ScheduleFragment {
            val fragmentFirst = ScheduleFragment()
            val args = Bundle()
//            args.putInt("page", page)
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
//        page = arguments!!.getInt("page", 0)
        date = arguments!!.getString("date")
        day = arguments!!.getString("day")
        time = arguments!!.getStringArray("time")
        subject = arguments!!.getStringArray("subject")
        classroom = arguments!!.getStringArray("classroom")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false)
        val time1 = view.findViewById<TextView>(R.id.time1)
        val subj1 = view.findViewById<TextView>(R.id.subject1)
        val class1 = view.findViewById<TextView>(R.id.class1)

        val time2 = view.findViewById<TextView>(R.id.time2)
        val subj2 = view.findViewById<TextView>(R.id.subject2)
        val class2 = view.findViewById<TextView>(R.id.class2)

        val time3 = view.findViewById<TextView>(R.id.time3)
        val subj3 = view.findViewById<TextView>(R.id.subject3)
        val class3 = view.findViewById<TextView>(R.id.class3)

        val time4 = view.findViewById<TextView>(R.id.time4)
        val subj4 = view.findViewById<TextView>(R.id.subject4)
        val class4 = view.findViewById<TextView>(R.id.class4)

        val time5 = view.findViewById<TextView>(R.id.time5)
        val subj5 = view.findViewById<TextView>(R.id.subject5)
        val class5 = view.findViewById<TextView>(R.id.class5)

        val time6 = view.findViewById<TextView>(R.id.time6)
        val subj6 = view.findViewById<TextView>(R.id.subject6)
        val class6 = view.findViewById<TextView>(R.id.class6)

        val time7 = view.findViewById<TextView>(R.id.time7)
        val subj7 = view.findViewById<TextView>(R.id.subject7)
        val class7 = view.findViewById<TextView>(R.id.class7)

        time1.text = time!![1]
        subj1.text = subject!![1]
        class1.text = classroom!![1]

        time2.text = time!![2]
        subj2.text = subject!![2]
        class2.text = classroom!![2]

        time3.text = time!![3]
        subj3.text = subject!![3]
        class3.text = classroom!![3]

        time4.text = time!![4]
        subj4.text = subject!![4]
        class4.text = classroom!![4]

        time5.text = time!![5]
        subj5.text = subject!![5]
        class5.text = classroom!![5]

        time6.text = time!![6]
        subj6.text = subject!![6]
        class6.text = classroom!![6]

        time7.text = time!![7]
        subj7.text = subject!![7]
        class7.text = classroom!![7]
        return view
    }
}