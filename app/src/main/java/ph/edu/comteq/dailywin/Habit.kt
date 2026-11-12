package ph.edu.comteq.dailywin

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dateCreated: Long = System.currentTimeMillis()
)


val habitList = listOf(
    Habit(1, "Drink water"),
    Habit(2, "Study 30 minutes"),
    Habit(3, "Exercise"),
    Habit(4, "Read a book")
)