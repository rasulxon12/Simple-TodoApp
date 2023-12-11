package uz.akra.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.akra.todoapp.adapters.SpinnerBaseAdapter
import uz.akra.todoapp.databinding.ActivityChangeTodoBinding
import uz.akra.todoapp.modes.MyDegree
import uz.akra.todoapp.modes.MyType
import uz.akra.todoapp.utils.MySharedPreference
import uz.akra.todoapp.utils.SpinnerList

class ChangeTodoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityChangeTodoBinding.inflate(layoutInflater) }
    lateinit var spinnerBaseAdapter: SpinnerBaseAdapter
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

         MySharedPreference.init(this)

         spinnerBaseAdapter = SpinnerBaseAdapter(SpinnerList.spinList)
         binding.spinDegree.adapter = spinnerBaseAdapter



         val list = MySharedPreference.list
         val index = intent.getIntExtra("keyTod", 0)
         val myToDo = list[index]

         binding.apply {
             txtTile.text = myToDo.title
             edtDesc.setText(myToDo.description)

             when(myToDo.degree){
                 MyDegree.URGENT-> spinDegree.setSelection(0)
                 MyDegree.HIGH-> spinDegree.setSelection(1)
                 MyDegree.NORMAL-> spinDegree.setSelection(2)
                 MyDegree.LOW-> spinDegree.setSelection(3)
             }
             edtDate.setText(myToDo.data)
             edtDeadline.setText(myToDo.deadline)

             when(myToDo.type){
                 MyType.OPEN -> radioOpen.isChecked = true
                 MyType.DEVELOPMENT -> radioDev.isChecked = true
                 MyType.UPLOADING -> radioUpload.isChecked = true
                 MyType.REJECTED -> radioReject.isChecked = true
                 MyType.CLOSED -> radioClosed.isChecked = true
             }


             btnSave.setOnClickListener {
                 myToDo.title = txtTile.text.toString()
                 myToDo.description = edtDesc.text.toString()

                 when(spinDegree.selectedItemPosition){
                     0->myToDo.degree = MyDegree.URGENT
                     1->myToDo.degree = MyDegree.HIGH
                     2->myToDo.degree = MyDegree.NORMAL
                     3->myToDo.degree = MyDegree.LOW
                 }

                 myToDo.data = edtDate.text.toString()
                 myToDo.deadline = edtDeadline.text.toString()

                 if (radioOpen.isChecked){
                     myToDo.type = MyType.OPEN
                 }else if (radioDev.isChecked){
                     myToDo.type = MyType.DEVELOPMENT
                 }else if(radioUpload.isChecked){
                     myToDo.type = MyType.UPLOADING
                 }else if (radioReject.isChecked){
                     myToDo.type = MyType.REJECTED
                 }else{
                     myToDo.type = MyType.CLOSED
                 }

                 list[index] = myToDo
                 MySharedPreference.list = list
                 Toast.makeText(this@ChangeTodoActivity, "Saved", Toast.LENGTH_SHORT).show()
                 finish()
             }

         }

    }
}