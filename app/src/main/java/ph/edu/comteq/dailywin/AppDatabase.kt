package ph.edu.comteq.dailywin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Habit::class, Completion::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun habitActions(): HabitActions

    companion object {
        private var instance: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dailywin_database"
                ).build()
            }
            return instance!!
        }
    }
}