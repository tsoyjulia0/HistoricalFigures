package com.example.historicalfigures.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.historicalfigures.databinding.ItemHistoricalFigureBinding
import com.example.historicalfigures.model.entity.HistoricalFigure

class HistoricalFigureAdapter(
    private var figures: MutableList<HistoricalFigure> = arrayListOf()
) : RecyclerView.Adapter<HistoricalFigureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoricalFigureBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(figures[position])
    }

    override fun getItemCount(): Int {
        return figures.size
    }

    fun submitList(newList: List<HistoricalFigure>) {
        val diffResult = DiffUtil.calculateDiff(OfferDiffCallback(figures, newList))
        figures.clear()
        figures = ArrayList(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
        private val binding: ItemHistoricalFigureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(figure: HistoricalFigure) {
            val info = figure.info
            with(binding) {
                nameTextView.text = figure.name
                titleTextView.text = figure.title

                if (info.born == null) {
                    bornTextView.text = "No data available"
                } else {bornTextView.text = info.born}

                if (info.died == null) {
                    diedTextView.text = "No data available"
                } else {diedTextView.text = info.born}

                if (info.years_active == null) {
                    yearsTextView.text = "No data available"
                } else {yearsTextView.text = info.years_active}

                if (info.nationality == null) {
                    nationalityTextView.text = "No data available"
                } else {nationalityTextView.text = info.nationality}


//                if (info.spouse.isNullOrEmpty()) {
//                    spouseTextView.visibility = View.GONE
//                } else {
//                    spouseTextView.visibility = View.VISIBLE
//                    spouseTextView.text = if (info.spouse.size == 1) {
//                        "Spouse: ${info.spouse[0]}"
//                    } else {
//                        "Spouses: ${info.spouse.joinToString()}"
//                    }
//                }
            }
        }
    }

    private class OfferDiffCallback(
        private val oldList: List<HistoricalFigure>,
        private val newList: List<HistoricalFigure>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}
