package ph.edu.comteq.dailywin

data class Habit(
    val id: Int,
    val name: String,
    val streak: Int
)

// Sample habits
val habitList = listOf(
    Habit(1, "Drink water", 5),
    Habit(2, "Study 25 minutes", 3),
    Habit(3, "Read a book", 0),
    Habit(4, "Jogging", 7)
)