package profs.roomcounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Perfomance::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PerfomanceDatabase: RoomDatabase() {

    abstract fun getPerfomanceDao(): PerfomanceDao

    companion object {

        @Volatile
        private var instance: PerfomanceDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PerfomanceDatabase::class.java,
            "PerfomanceDatabase"
        ).build()
    }
}