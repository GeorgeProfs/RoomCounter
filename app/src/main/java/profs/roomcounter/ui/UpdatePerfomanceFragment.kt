package profs.roomcounter.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import profs.roomcounter.R
import profs.roomcounter.data.Perfomance

/**
 * A simple [Fragment] subclass.
 */
class UpdatePerfomanceFragment : BaseFragment() {

    private var perfomance: Perfomance? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_perfomance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            perfomance = UpdatePerfomanceFragmentArgs.fromBundle(it).perfomance
        //    edit_text_title.setText(note?.title)
          //  edit_text_note.setText(note?.note)
        }

    }


}
