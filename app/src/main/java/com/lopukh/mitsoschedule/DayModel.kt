package com.lopukh.mitsoschedule

class DayModel(
    var date: String,
    var day: String,
    var time: Array<String?>,
    var subject: Array<String?>,
    classroom: Array<String?>
) {
    var classroom: Array<String?>

    init {
        val newClassroom: Array<String?> = arrayOfNulls(subject.size)
        var last = 0
        for (i in 0 until subject.size) {
            if (!subject[i].equals("Нет занятий")) {
                newClassroom[i] = classroom[last]
                last++
            }
        }
        this.classroom = newClassroom
    }
}