package profs.roomcounter.data

import androidx.room.*

@Dao
interface ResultDao {

    @Insert
    suspend fun addResult(result: Result)

    @Query("SELECT * FROM Result ORDER BY id DESC")
    suspend fun getAllResults(): List<Result>

    @Insert
    suspend fun addAllResult(vararg results: Result)

    @Update
    suspend fun updateResult(result: Result)

    @Delete
    suspend fun deleteResult(result: Result)
}