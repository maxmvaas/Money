package ru.maxmvaas.money.presentation.currencies_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import ru.maxmvaas.money.R
import ru.maxmvaas.money.data.model.Currency
import ru.maxmvaas.money.databinding.ItemCurrencyBinding

class CurrenciesAdapter : RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder>() {
    private val items = mutableListOf<Currency>()

    var onItemClick: ((Currency) -> Unit)? = null

    fun setItems(items: List<Currency>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewRecycled(holder: CurrencyViewHolder) {
        holder.unbind()
    }

    override fun getItemCount() = items.size

    inner class CurrencyViewHolder(
        private var binding: ItemCurrencyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            itemView.setOnClickListener {
                onItemClick?.invoke(currency)
            }

            binding.apply {
                textViewTitle.text = currency.name
                textViewRate.text =
                    itemView.resources.getString(R.string.rate_format, currency.rate)
            }
        }

        fun unbind() {
            itemView.setOnClickListener(null)
        }
    }
}