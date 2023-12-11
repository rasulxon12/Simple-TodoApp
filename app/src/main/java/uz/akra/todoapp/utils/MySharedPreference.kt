package uz.akra.todoapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.akra.todoapp.modes.MyToDo

object MySharedPreference {
    private const val NAME = "catch_file_name"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor)->Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var list:ArrayList<MyToDo>
        get() = gsonToList(preferences.getString("todoList", "[]")!!)
        set(value) = preferences.edit {
            it.putString("todoList", listToGson(value))
        }

    private fun gsonToList(str:String):ArrayList<MyToDo>{
        val list = ArrayList<MyToDo>()
        val type = object : TypeToken<ArrayList<MyToDo>>(){}.type
        list.addAll(Gson().fromJson(str,type))

        return list

    }

    private fun listToGson(list:ArrayList<MyToDo>):String{
        return Gson().toJson(list)
    }

}