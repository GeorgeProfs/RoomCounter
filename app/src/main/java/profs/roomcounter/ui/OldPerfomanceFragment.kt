package profs.roomcounter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_old_perfomance.*
import profs.roomcounter.R

/**
 * A simple [Fragment] subclass.
 */
class OldPerfomanceFragment : BaseFragment() {

    private val calcPresenter = CalcPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_old_perfomance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rootView = getView() as View

        val textInputLayouts = Utils.findViewsWithType(
            rootView, TextInputLayout::class.java
        )

        button_nextPerfomance.setOnClickListener {
            var valueExist = true
            val listD = mutableListOf<String>()
            for (textInputLayout in textInputLayouts) {
                val editTextString = textInputLayout.editText!!.text.toString()
                if (editTextString.isEmpty()) {
                    textInputLayout.error = resources.getString(R.string.no_value_error)
                    valueExist = false
                } else {
                    textInputLayout.error = null
                    listD.add(editTextString)
                }
            }

            if (valueExist) {
                val oldValueAddToModel = calcPresenter.onAllOldFieldsValid(listD)
                if (oldValueAddToModel) {
                    val action = OldPerfomanceFragmentDirections.actionGoToNewPerfomance()
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }

        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Выберите дату")
        val picker = builder.build()
        oldDate.setEndIconOnClickListener {
            picker.show(childFragmentManager, "Date Picker")

            picker.addOnCancelListener {
                context?.toast("dialog canceled")
            }

            picker.addOnNegativeButtonClickListener {
                context?.toast("dialog negative button clicked")
            }

            picker.addOnPositiveButtonClickListener {
                it?.let {
                    editOldDate.setText(it)
                }
            }

        }
    }

}

