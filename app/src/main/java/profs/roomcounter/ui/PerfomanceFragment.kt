package profs.roomcounter.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_perfomance.*
import kotlinx.coroutines.launch
import profs.roomcounter.R
import profs.roomcounter.data.RoomCounterDatabase

/**
 * A simple [Fragment] subclass.
 */
class PerfomanceFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfomance, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view_perfomances.setHasFixedSize(true)
        recycler_view_perfomances.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val perfomances = RoomCounterDatabase(it).getPerfomanceDao().getAllPerfomances()
                recycler_view_perfomances.adapter = PerfomanceAdapter(perfomances)
            }
        }
    }

}
