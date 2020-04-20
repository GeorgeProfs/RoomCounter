package profs.roomcounter.ui

import kotlinx.coroutines.flow.Flow

interface MainContract {
    interface View {
      //  fun prepareFields(): Flow<Any>
    }

    interface Presenter {
        fun onAllOldFieldsValid(listOfPerfomanceValues: MutableList<String>): Boolean
        fun onButtonSuccess()
    }

    interface Model {
        fun storeOldFieldsValues(listOfOldPerfomanceValues: MutableList<String>)
        fun addOldPerfomanceIntoDataClass()
    }
}