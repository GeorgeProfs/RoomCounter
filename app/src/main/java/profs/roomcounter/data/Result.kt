package profs.roomcounter.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Perfomance::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("oldPerfomanceId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Perfomance::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("newPerfomanceId"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = ["oldPerfomanceId", "newPerfomanceId"])]

)
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val oldPerfomanceId: Int,
    val newPerfomanceId: Int,
    val monthResult: String
) : Serializable