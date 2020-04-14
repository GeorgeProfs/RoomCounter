package profs.roomcounter.ui

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun TextView.setText(date: Long) = setText(SimpleDateFormat("dd-MM-yyyy").format(Date(date)))

fun EditText.validation(value: String): Int {
    if (value.isEmpty())
    {
        this.error = "note required"
        this.requestFocus()
        return 0
    }
    return 1
}



