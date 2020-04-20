package profs.roomcounter.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
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

object Utils {

    fun <T : View> findViewsWithType(root: View, type: Class<T>): List<T> {
        val views = ArrayList<T>()
        findViewsWithType(root, type, views)
        return views
    }

    private fun <T : View> findViewsWithType(view: View, type: Class<T>, views: MutableList<T>) {
        if (type.isInstance(view)) {
            type.cast(view)?.let { views.add(it) }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findViewsWithType(view.getChildAt(i), type, views)
            }
        }
    }
}


