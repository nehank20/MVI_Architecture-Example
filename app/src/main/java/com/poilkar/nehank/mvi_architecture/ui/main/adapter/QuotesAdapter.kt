package com.poilkar.nehank.mvi_architecture.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poilkar.nehank.mvi_architecture.data.model.Quotes
import com.poilkar.nehank.mvi_architecture.databinding.ViewHolderQuotesBinding

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.MyViewHolder>() {

    var list = mutableListOf<Quotes.Result>()

    fun addItems(list: List<Quotes.Result>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderQuotesBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesAdapter.MyViewHolder {
        val binding =
            ViewHolderQuotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesAdapter.MyViewHolder, position: Int) {
        val binding = holder.viewDataBinding
        val item = this.list[position]

        binding.tvTitle.text = item.content
        binding.tvDesc.text = item.author

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}