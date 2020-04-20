package profs.roomcounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Perfomance::class, Result::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RoomCounterDatabase: RoomDatabase() {

    abstract fun getResultDao(): ResultDao
    abstract fun getPerfomanceDao(): PerfomanceDao

    companion object {

        @Volatile
        private var instance: RoomCounterDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomCounterDatabase::class.java,
            "RoomCounterDatabase"
        ).build()
    }
}