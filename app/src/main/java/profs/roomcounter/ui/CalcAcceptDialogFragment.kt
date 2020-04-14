package profs.roomcounter.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import profs.roomcounter.R
import profs.roomcounter.data.Perfomance
import profs.roomcounter.data.Result


class CalcAcceptDialogFragment (result: String, oldPerfomance: Perfomance, newPerfomance: Perfomance) : DialogFragment(){

    val result = result
    val oldPerfomance = oldPerfomance
    val newPerfomance = newPerfomance
    val newResult: Result = Result(oldPerfomance.id, newPerfomance.id, result)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            //TODO: make view element, common man
            builder.setTitle("Важное сообщение!")
                .setMessage(result)
                .setIcon(R.drawable.ic_format_list)
                .setPositiveButton("Записать результат") {
                        dialog, id -> CalcFragment().setOnClick(oldPerfomance, newPerfomance, newResult)
                }
                .setNegativeButton("Сбросить"){
                        dialog, id ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}