package uz.akra.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.akra.todoapp.adapters.MyExpandAdapter
import uz.akra.todoapp.databinding.ActivityListTodoBinding
import uz.akra.todoapp.modes.MyToDo
import uz.akra.todoapp.modes.MyType
import uz.akra.todoapp.utils.MySharedPreference

class ListTodoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityListTodoBinding.inflate(layoutInflater) }
    lateinit var titleList: ArrayList<String>
    lateinit var map: HashMap<String, ArrayList<MyToDo>>
    lateinit var myExpandAdapter: MyExpandAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.expandView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val myTodo:MyToDo = map[titleList[groupPosition]]?.get(childPosition)!!
            val index = MySharedPreference.list.indexOf(myTodo)

            val intent = Intent(this, ChangeTodoActivity::class.java)
            intent.putExtra("keyTod", index)
            startActivity(intent)
            true

        }

    }

    override fun onResume() {
        super.onResume()
        loadData()

        myExpandAdapter = MyExpandAdapter(titleList, map)
        binding.expandView.setAdapter(myExpandAdapter)
    }

    private fun loadData() {

        MySharedPreference.init(this)
        titleList = ArrayList()
        map = HashMap()

        titleList.add("Open")
        titleList.add("Development")
        titleList.add("Uploading")
        titleList.add("Reject")
        titleList.add("Closed")

        val allList = MySharedPreference.list

        var openList = ArrayList<MyToDo>()
        var devList = ArrayList<MyToDo>()
        var uplList = ArrayList<MyToDo>()
        var rejList = ArrayList<MyToDo>()
        var closedList = ArrayList<MyToDo>()

        for (m in allList) {
            when (m.type) {
                MyType.OPEN -> openList.add(m)
                MyType.DEVELOPMENT -> devList.add(m)
                MyType.UPLOADING -> uplList.add(m)
                MyType.REJECTED -> rejList.add(m)
                MyType.CLOSED -> closedList.add(m)
            }
        }

        map[titleList[0]] = openList
        map[titleList[1]] = devList
        map[titleList[2]] = uplList
        map[titleList[3]] = rejList
        map[titleList[4]] = closedList
    }
}