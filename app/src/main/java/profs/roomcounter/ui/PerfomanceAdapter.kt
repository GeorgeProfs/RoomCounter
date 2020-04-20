package profs.roomcounter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import profs.roomcounter.R
import profs.roomcounter.data.Perfomance
import kotlinx.android.synthetic.main.perfomance_layout.view.*

class PerfomanceAdapter(private val perfomances: List<Perfomance>) : RecyclerView.Adapter<PerfomanceAdapter.PerfomanceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfomanceViewHolder {
        return PerfomanceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.perfomance_layout, parent, false)
        )
    }

    override fun getItemCount() = perfomances.size

    override fun onBindViewHolder(holder: PerfomanceViewHolder, position: Int) {
        holder.view.date_view_perfomance.text = perfomances[position].coldWater.toString()

        holder.view.setOnClickListener {
            val action = PerfomanceFragmentDirections.actionUpdatePerfomance()
            action.perfomance = perfomances[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class PerfomanceViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}