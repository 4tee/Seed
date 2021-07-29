package com.demo.seed.ui.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.seed.databinding.ItemCurrencyBinding
import com.demo.seed.ui.model.CurrencyInfo

class CurrencyListAdapter(private val itemClickedListener: OnItemClickListener) :
    ListAdapter<CurrencyInfo, CurrencyListAdapter.CurrencyViewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CurrencyViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val currencyInfo = getItem(position)
                        itemClickedListener.onItemClicked(currencyInfo)
                    }
                }
            }
        }

        fun bind(currencyInfo: CurrencyInfo) {
            binding.apply {
                textCurrencyName.text = currencyInfo.name
                textCurrencyCode.text = currencyInfo.symbol
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClicked(currencyInfo: CurrencyInfo)
    }

    class DiffCallback : DiffUtil.ItemCallback<CurrencyInfo>() {
        override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo) =
            oldItem.id == newItem.id // id is unique

        override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo) =
            oldItem == newItem
    }
}