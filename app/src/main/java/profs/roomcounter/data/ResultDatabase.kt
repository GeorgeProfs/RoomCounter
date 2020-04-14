package profs.roomcounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Result::class, Perfomance::class],
    version = 1,
    exportSchema = false
)
abstract class ResultDatabase: RoomDatabase() {

    abstract fun getResultDao(): ResultDao
    abstract fun getPerfomanceDao(): PerfomanceDao

    companion object {

        @Volatile
        private var instance: ResultDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ResultDatabase::class.java,
            "ResultDatabase"
        ).build()
    }
}