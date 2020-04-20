package profs.roomcounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Perfomance(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val hotWater: Int,
    val coldWater: Int,
    val t1: Int,
    val t2: Int,
    val t3: Int
):Serializable