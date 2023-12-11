package uz.akra.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.akra.todoapp.adapters.MyExpandAdapter
import uz.akra.todoapp.databinding.ActivityMainBinding
import uz.akra.todoapp.modes.MyToDo
import uz.akra.todoapp.utils.SpinnerList

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        SpinnerList.addData()

        binding.btnTodolist.setOnClickListener{
            startActivity(Intent(this, ListTodoActivity::class.java))
        }

        binding.btnAddtodo.setOnClickListener{
            startActivity(Intent(this, AddTodoActivity::class.java))
        }


    }
}