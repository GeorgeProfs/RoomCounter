package profs.roomcounter.ui

class CalcPresenter : MainContract.Presenter {

    private val calcModel = CalcModel()

    override fun onAllOldFieldsValid(listOfPerfomanceValues: MutableList<String>): Boolean {
        calcModel.storeOldFieldsValues(listOfPerfomanceValues)
        return true
    }

    override fun onButtonSuccess() {
        calcModel.addOldPerfomanceIntoDataClass()
    }

}