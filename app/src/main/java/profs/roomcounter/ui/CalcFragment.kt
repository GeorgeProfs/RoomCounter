package profs.roomcounter.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_calc.*
import kotlinx.coroutines.launch
import profs.roomcounter.R
import profs.roomcounter.calcLogic.CalcLogic
import profs.roomcounter.data.Perfomance
import profs.roomcounter.data.PerfomanceDatabase
import profs.roomcounter.data.Result
import profs.roomcounter.data.ResultDatabase
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CalcFragment : BaseFragment() {

    private var result: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calc, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        builder.setTitleText("Выберите диапазон дат")
        val picker = builder.build()

        datePickerButton.setOnClickListener {
            picker.show(childFragmentManager, "Date Range Picker")

            picker.addOnCancelListener {
                context?.toast("dialog canceled")
            }

            picker.addOnNegativeButtonClickListener {
                context?.toast("dialog negative button clicked")
            }

            picker.addOnPositiveButtonClickListener {
                it.first?.let {
                    editOldDate.setText(it)
                }
                it.second?.let {
                    editNewDate.setText(it)
                }
            }
        }

        button_calc.setOnClickListener { view ->

            val oldDate = editOldDate.text.toString().trim()
            val oldHotWater = editOldHotWater.text.toString().trim()
            val oldColdWater = editOldColdWater.text.toString().trim()
            val oldT1 = editOldT1.text.toString().trim()
            val oldT2 = editOldT2.text.toString().trim()
            val oldT3 = editOldT3.text.toString().trim()

            val newDate = editNewDate.text.toString().trim()
            val newHotWater = editNewHotWater.text.toString().trim()
            val newColdWater = editNewColdWater.text.toString().trim()
            val newT1 = editNewT1.text.toString().trim()
            val newT2 = editNewT2.text.toString().trim()
            val newT3 = editNewT3.text.toString().trim()

            //TODO: Validation for Date Fields

            if (editOldHotWater.validation(oldHotWater) == 0 ||
                editNewHotWater.validation(newHotWater) == 0 ||
                editOldColdWater.validation(oldColdWater) == 0 ||
                editNewColdWater.validation(newColdWater) == 0 ||
                editOldT1.validation(oldT1) == 0 ||
                editNewT1.validation(newT1) == 0 ||
                editOldT2.validation(oldT2) == 0 ||
                editNewT2.validation(newT2) == 0 ||
                editOldT3.validation(oldT3) == 0 ||
                editNewT3.validation(newT3) == 0
            ) {
                return@setOnClickListener
            }

            val oldPerfomance = Perfomance(
                oldDate,
                oldHotWater.toInt(),
                oldColdWater.toInt(),
                oldT1.toInt(),
                oldT2.toInt(),
                oldT3.toInt()
            )
            val newPerfomance = Perfomance(
                newDate,
                newHotWater.toInt(),
                newColdWater.toInt(),
                newT1.toInt(),
                newT2.toInt(),
                newT3.toInt()
            )
            result = CalcLogic().calcResultOfMonth(oldPerfomance, newPerfomance)

            val newResult: Result = Result(oldPerfomance.id, newPerfomance.id, result.toString())

            launch {

                context?.let {
                    PerfomanceDatabase(it).getPerfomanceDao()
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
        }
    }

    fun setOnClick(oldPerfomance: Perfomance, newPerfomance: Perfomance, result: Result) {

        launch {

            context?.let {
                PerfomanceDatabase(it).getPerfomanceDao()
                    .addAllPerfomances(oldPerfomance, newPerfomance)
                it.toast("Perfomances saved")
                ResultDatabase(it).getResultDao().addResult(result)
                it.toast("Result saved")
            }

        }
    }
}

/*
            button_dialog.setOnClickListener {
                fragmDialog.show(childFragmentManager, "Dialog")
                //TODO: make Snackbar
            }
 */
