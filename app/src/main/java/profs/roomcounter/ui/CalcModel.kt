package profs.roomcounter.ui

import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import profs.roomcounter.data.Perfomance
import profs.roomcounter.data.RoomCounterDatabase

class CalcModel: MainContract.Model, BaseFragment(){

    var oldDate: String? = null
    var oldHotWater: String? = null
    var oldColdWater: String? = null
    var oldT1: String? = null
    var oldT2: String? = null
    var oldT3: String? = null

    override fun storeOldFieldsValues(listOfOldPerfomanceValues: MutableList<String>) {
        oldDate = listOfOldPerfomanceValues[0]
        oldHotWater = listOfOldPerfomanceValues[1]
        oldColdWater = listOfOldPerfomanceValues[2]
        oldT1 = listOfOldPerfomanceValues[3]
        oldT2 = listOfOldPerfomanceValues[4]
        oldT3 = listOfOldPerfomanceValues[5]
    }

    override fun addOldPerfomanceIntoDataClass() {
        val oldPerfomance = Perfomance(
            id = id,
            date = "2020",
            hotWater = 15,
            coldWater = 16,
            t1 = 120,
            t2 = 13,
            t3 = 14
        )
        job = GlobalScope.launch {

            context?.let {
                RoomCounterDatabase(it).getPerfomanceDao()
                    .addPerfomance(oldPerfomance)
                it.toast("Perfomances saved")
//                RoomCounterDatabase(it).getResultDao().addResult(newResult)
//                    it.toast("Result saved")
            }

        }
    }
}