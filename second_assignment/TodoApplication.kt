package second_assignment

import java.io.File
import java.io.FileWriter
import java.util.UUID

data class TodoTask(
    val id: UUID,
    val title: String,
    val content: String,
    var isComplete: Boolean,
)

const val TODO_PATH = "second_assignment/todo.txt"

fun main() {
    val todoList = mutableListOf<TodoTask>()

    while (true) {
        when (readln()) {
            "할 일 추가" -> addTodoTask()
            "할 일 목록 조회" -> readTodoTextFile()
            "할 일 완료하기" -> todoList.completeTodoTask()
            "할 일 삭제하기" -> todoList.deleteTodoTask()
            "그만 사용할래요" -> break
            else -> println("잘못 입력 하셨어요.")
        }
    }
}

fun addTodoTask() {
    println("첫 번째로 제목을 입력하고 두 번째로 내용을 입력하세요.")
    val todoTask = TodoTask(
        id = UUID.randomUUID(),
        title = readln(),
        content = readln(),
        isComplete = false,
    )
    addTodoTextFile(todoTask)
}

fun MutableList<TodoTask>.getTodoTask() {
    this.forEach { todoTask ->
        println(
            "id : ${todoTask.id}, title : ${todoTask.title}, content : ${todoTask.content}, isComplete : ${todoTask.isComplete}"
        )
    }
}

fun MutableList<TodoTask>.completeTodoTask() {
    println("완료할 일의 id를 입력해주세요 ex))${UUID.randomUUID()}")
    val findTask = this.single { todoTask -> todoTask.id == UUID.fromString(readln()) }
    val completeTaskIndex = this.indexOf(findTask)
    this[completeTaskIndex].isComplete = true
}

fun MutableList<TodoTask>.deleteTodoTask() {
    println("삭제할 일의 id를 입력해주세요 ex))${UUID.randomUUID()}")
    val deleteTask = this.single { todoTask -> todoTask.id == UUID.fromString(readln()) }
    this.remove(deleteTask)
}

fun addTodoTextFile(todoTask: TodoTask) {
    FileWriter(TODO_PATH, true).use { it.write(todoTask.toString()) }
}

fun readTodoTextFile() {
    println(File(TODO_PATH).readText())
}
