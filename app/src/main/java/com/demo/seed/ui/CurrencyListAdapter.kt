package com.demo.seed.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.seed.data.CurrencyInfo
import com.demo.seed.databinding.ItemCurrencyBinding

class CurrencyListAdapter : ListAdapter<CurrencyInfo, CurrencyListAdapter.CurrencyViewHolder>(DiffCallback()) {

    class CurrencyViewHolder(private val binding: ItemCurrencyBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyInfo: CurrencyInfo) {
            binding.apply {
                textCurrencyName.text = currencyInfo.name
                textCurrencyCode.text = currencyInfo.symbol
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<CurrencyInfo>() {
        override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo) =
            oldItem.id == newItem.id // id is unique

        override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo) =
            oldItem == newItem
    }
}