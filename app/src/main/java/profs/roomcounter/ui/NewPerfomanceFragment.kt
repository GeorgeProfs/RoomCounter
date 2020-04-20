package profs.roomcounter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_new_perfomance.*
import kotlinx.coroutines.launch
import profs.roomcounter.R
import profs.roomcounter.data.Perfomance
import profs.roomcounter.data.Result
import profs.roomcounter.data.RoomCounterDatabase

/**
 * A simple [Fragment] subclass.
 */
class NewPerfomanceFragment : BaseFragment(), MainContract.View {

    private var result: Int? = null
    private val calcPresenter = CalcPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_perfomance, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Выберите дату")
        val picker = builder.build()
        newDate.setEndIconOnClickListener {
            picker.show(childFragmentManager, "Date Picker")

            picker.addOnCancelListener {
                context?.toast("dialog canceled")
            }

            picker.addOnNegativeButtonClickListener {
                context?.toast("dialog negative button clicked")
            }

            picker.addOnPositiveButtonClickListener {
                it?.let {
                    editNewDate.setText(it)
                }
            }
        }

        button_calcPerfomances.setOnClickListener {
            calcPresenter.onButtonSuccess()
            val oldPerfomance = Perfomance(
                id = id,
                date = "2020",
                hotWater = 15,
                coldWater = 16,
                t1 = 120,
                t2 = 13,
                t3 = 14
            )
            launch {

                context?.let {
                    RoomCounterDatabase(it).getPerfomanceDao()
                        .addPerfomance(oldPerfomance)
                    it.toast("Perfomances saved")
//                RoomCounterDatabase(it).getResultDao().addResult(newResult)
//                    it.toast("Result saved")
                }

            }
        }


  /*
        button_calc.setOnClickListener { view ->

            val oldPerfomance = Perfomance(
                id = id,
                date = oldDate,
                hotWater = oldHotWater.toInt(),
                coldWater = oldColdWater.toInt(),
                t1 = oldT1.toInt(),
                t2 = oldT2.toInt(),
                t3 = oldT3.toInt()
            )
            val newPerfomance = Perfomance(
                id = id,
                date = newDate,
                hotWater = newHotWater.toInt(),
                coldWater = newColdWater.toInt(),
                t1 = newT1.toInt(),
                t2 = newT2.toInt(),
                t3 = newT3.toInt()
            )
            result = CalcLogic().calcResultOfMonth(oldPerfomance, newPerfomance)

            val newResult: Result = Result(
                id = id,
                oldPerfomanceId = oldPerfomance.id,
                newPerfomanceId = newPerfomance.id,
                monthResult = result.toString())

            launch {

                context?.let {
                    RoomCounterDatabase(it).getPerfomanceDao()
                        .addAllPerfomances(oldPerfomance, newPerfomance)
                    it.toast("Perfomances saved")
//                    ResultDatabase(it).getResultDao().addResult(newResult)
//                    it.toast("Result saved")
                }

            }
            /*
            result?.let {
                val dialogFragment =
                    CalcAcceptDialogFragment(it.toString(), oldPerfomance, newPerfomance)
                dialogFragment.show(childFragmentManager, "Dialog")
                //TODO: make Snackbar
            }*/
        }*/
    }

    fun setOnClick(oldPerfomance: Perfomance, newPerfomance: Perfomance, result: Result) {

        launch {

            context?.let {
                RoomCounterDatabase(it).getPerfomanceDao()
                    .addAllPerfomances(oldPerfomance, newPerfomance)
                it.toast("Perfomances saved")
                RoomCounterDatabase(it).getResultDao().addResult(result)
                it.toast("Result saved")
            }

        }
    }
}
