package com.example.assessmentspectrumglobal.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.ItemSelection
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.databinding.MemberDataItemBinding

/**
Created by kiranb on 2/8/20
 */
class MemberListAdapter (var list: List<MemberEntity>, val listener:ItemSelection) :
    RecyclerView.Adapter<MemberListAdapter.ViewHolder>(),Filterable {

    private var orignalList: List<MemberEntity> = list

    private lateinit var binding: MemberDataItemBinding

    init {
        orignalList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MemberDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.memberDataItemBinding.favouriteCheck.setOnCheckedChangeListener { buttonView, isChecked ->
            item.favourite = isChecked
            listener.onMarkMemberFavourite(item)
        }
        holder.memberDataItemBinding.item = item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun showSortedListByName(ascending: Boolean) {
        val newData = list.toMutableList()
        if (ascending) {
            newData.sortBy { it.name }
        } else
            newData.sortByDescending { it.name }
        setData(newData)

    }



    fun showSortedListByAge(ascending: Boolean) {
        val newData = list.toMutableList()
        if (ascending) {
            newData.sortBy { it.age }
        } else
            newData.sortByDescending { it.age }
        setData(newData)

    }

    fun showOrignalList() {
        setData(orignalList)
    }

    private fun setData(newData: List<MemberEntity>) {
        val diffResult = DiffUtil.calculateDiff(MemberListDiffCallback(list,newData))
        val updatedList = list.toMutableList()
        updatedList.clear()
        updatedList.addAll(newData)
        list = updatedList.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: MemberDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var memberDataItemBinding: MemberDataItemBinding = binding
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private lateinit var newList: List<MemberEntity>

            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                val filteredList: MutableList<MemberEntity> = ArrayList()
                if (charString.isNotEmpty()) {
                    for (data in orignalList) {
                        if (data.name.toLowerCase().contains(charSequence.toString())) {
                            filteredList.add(data)
                        }
                    }
                    newList = filteredList
                } else {
                    newList = orignalList
                }
                val filterResults = FilterResults()
                filterResults.values = newList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                setData(filterResults.values as List<MemberEntity>)
            }
        }
    }
}