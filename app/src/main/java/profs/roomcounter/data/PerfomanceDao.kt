package profs.roomcounter.data

import androidx.room.*

@Dao
interface PerfomanceDao {

    @Insert
    suspend fun addPerfomance(perfomance: Perfomance)

    @Query("SELECT * FROM Perfomance ORDER BY id DESC")
    suspend fun getAllPerfomances(): List<Perfomance>

    @Insert
    suspend fun addAllPerfomances(vararg perfomance: Perfomance)

    @Update
    suspend fun updatePerfomance(perfomance: Perfomance)

    @Delete
    suspend fun deletePerfomance(perfomance: Perfomance)
}