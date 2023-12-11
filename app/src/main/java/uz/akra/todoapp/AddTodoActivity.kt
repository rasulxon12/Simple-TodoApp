package uz.akra.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SpinnerAdapter
import android.widget.Toast
import uz.akra.todoapp.adapters.SpinnerBaseAdapter
import uz.akra.todoapp.databinding.ActivityAddTodoBinding
import uz.akra.todoapp.modes.MyDegree
import uz.akra.todoapp.modes.MyToDo
import uz.akra.todoapp.modes.MyType
import uz.akra.todoapp.utils.MySharedPreference
import uz.akra.todoapp.utils.SpinnerList

class AddTodoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddTodoBinding.inflate(layoutInflater) }
    lateinit var spinnerAdapter: SpinnerBaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MySharedPreference.init(this)
        spinnerAdapter = SpinnerBaseAdapter(SpinnerList.spinList)
        binding.spinDegree.adapter = spinnerAdapter



        binding.apply {
            btnSave.setOnClickListener{
                var degree: MyDegree = when(spinDegree.selectedItemPosition){
                    0->MyDegree.URGENT
                    1->MyDegree.HIGH
                    2->MyDegree.NORMAL
                    3->MyDegree.LOW

                    else -> {MyDegree.URGENT}
                }
                var todo = MyToDo(
                    edtTitle.text.toString(),
                    edtDesc.text.toString(),
                    degree,
                    edtDeadline.text.toString(),
                    MyType.OPEN
                )

                val list = MySharedPreference.list
                list.add(todo)
                MySharedPreference.list = list
                Toast.makeText(this@AddTodoActivity, "Saved", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

}