package uz.akra.todoapp.utils

import uz.akra.todoapp.R
import uz.akra.todoapp.modes.Degree

object SpinnerList {
    var spinList : ArrayList<Degree> = ArrayList()

    fun addData(){
        spinList.add(Degree(R.drawable.fred, "Urgent"))
        spinList.add(Degree(R.drawable.forange, "High"))
        spinList.add(Degree(R.drawable.fblue, "Normal"))
        spinList.add(Degree(R.drawable.fgray, "Low"))
    }

}