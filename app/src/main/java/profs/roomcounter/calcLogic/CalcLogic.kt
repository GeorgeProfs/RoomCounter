package profs.roomcounter.calcLogic

import profs.roomcounter.data.Perfomance

class CalcLogic {

    fun calcWaterDifference(oldPerfHotWater: Int, newPerfHotWater: Int, oldPerfColdWater: Int, newPerfColdWater: Int, divider: Int): Int {
        val hotLitersDiff = (newPerfHotWater - oldPerfHotWater) / divider / 1000
        val coldLitersDiff = (newPerfColdWater - oldPerfColdWater) / divider / 1000
        return (hotLitersDiff * HOTWATER + coldLitersDiff * COLDWATER + (hotLitersDiff+coldLitersDiff) * WATERDISPOSAL).toInt()
    }

    fun calcElectricityDifference(oldPerfT1: Int, newPerfT1: Int, oldPerfT2: Int, newPerfT2: Int, oldPerfT3: Int, newPerfT3: Int, divider: Int): Int{
        val t1Result = (newPerfT1 - oldPerfT1) / divider * T1
        val t2Result= (newPerfT2 - oldPerfT2) / divider * T2
        val t3Result= (newPerfT3 - oldPerfT3) / divider * T3
        return (t1Result + t2Result + t3Result).toInt()
    }

    fun calcResultOfMonth(oldPerfomance: Perfomance, newPerfomance: Perfomance, divider: Int = 2): Int {
        val waterResult = calcWaterDifference(oldPerfomance.hotWater, newPerfomance.hotWater,oldPerfomance.coldWater, newPerfomance.coldWater, divider)
        val electricityResult = calcElectricityDifference(oldPerfomance.t1, newPerfomance.t1, oldPerfomance.t2, newPerfomance.t2, oldPerfomance.t3, newPerfomance.t3, divider)
        return waterResult + electricityResult
    }

    companion object TaxRates {
        const val HOTWATER = 198.19
        const val COLDWATER = 40.48
        const val T1 = 7.00
        const val T2 = 2.29
        const val T3 = 5.38
        const val WATERDISPOSAL = 59.14
    }
}