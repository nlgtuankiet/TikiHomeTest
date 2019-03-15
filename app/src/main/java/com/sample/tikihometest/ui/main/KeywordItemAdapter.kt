package com.sample.tikihometest.ui.main

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.tikihometest.databinding.MainKeywordItemBinding
import com.sample.tikihometest.domain.entity.KeywordItem
import com.sample.tikihometest.util.inflater

class KeywordItemAdapter constructor(
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<KeywordItemViewHolder>() {
    private val differ = AsyncListDiffer(this, DiffCallback)

    fun submitList(list: List<KeywordItem>?) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordItemViewHolder {
        return KeywordItemViewHolder(
            MainKeywordItemBinding.inflate(parent.inflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: KeywordItemViewHolder, position: Int) {
        holder.binding.apply {
            item = differ.currentList[position]
            setLifecycleOwner(lifecycleOwner)
            executePendingBindings()
        }
    }
}

class KeywordItemViewHolder(val binding: MainKeywordItemBinding) : RecyclerView.ViewHolder(binding.root)

internal object DiffCallback : DiffUtil.ItemCallback<KeywordItem>() {
    override fun areItemsTheSame(oldItem: KeywordItem, newItem: KeywordItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: KeywordItem, newItem: KeywordItem): Boolean {
        return oldItem == newItem
    }
}
