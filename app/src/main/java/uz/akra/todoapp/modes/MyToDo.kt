package uz.akra.todoapp.modes

import java.text.SimpleDateFormat
import java.util.Date

data class MyToDo(
    var title: String,
    var description: String,
    var degree: MyDegree,
    var deadline: String,
    var type: MyType,
    var data: String = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date())
)

enum class MyDegree {
    URGENT,
    HIGH,
    NORMAL,
    LOW
}

enum class MyType {
    OPEN,
    DEVELOPMENT,
    UPLOADING,
    REJECTED,
    CLOSED

}
