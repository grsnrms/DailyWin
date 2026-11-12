package ph.edu.comteq.dailywin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitActions {

    @Query("SELECT * FROM habits ORDER BY id")
    fun getAllHabits(): Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getHabit(id: Int): Habit?

    @Insert
    suspend fun insertHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT * FROM completions")
    fun getAllCompletions(): Flow<List<Completion>>

    @Insert
    suspend fun insertCompletion(completion: Completion)

    @Query("DELETE FROM completions WHERE habitId = :habitId AND date = :date")
    suspend fun deleteCompletion(habitId: Int, date: String)
}