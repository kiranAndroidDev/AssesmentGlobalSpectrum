package com.example.assessmentspectrumglobal.dashboard.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.ItemSelection
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.databinding.ClubDataItemBinding


/**
Created by kiranb on 31/7/20
 */
class ClubDataListAdapter(var list: List<CompanyEntity>, var listener: ItemSelection) :
    RecyclerView.Adapter<ClubDataListAdapter.ViewHolder>(), Filterable {

    private var orignalList: List<CompanyEntity>
    private lateinit var binding: ClubDataItemBinding

    init {
        orignalList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ClubDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.clubDataItemBinding.root.setOnClickListener {
            listener.onShowMembers(list[position].id, list[position].company!!)
        }
        holder.clubDataItemBinding.btnFollow.setOnClickListener {
            item.follow = !item.follow
            listener.onFollowCompany(item)
            holder.clubDataItemBinding.item = item

        }
        holder.clubDataItemBinding.favouriteCheck.setOnCheckedChangeListener { buttonView, isChecked ->
            item.favourite = isChecked
            listener.onMarkCompanyFavourite(item)
        }
        holder.clubDataItemBinding.item = item

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun showSortedList(ascending: Boolean) {
        val newData = list.toMutableList()
        if (ascending)
            newData.sortBy { it.company }
        else
            newData.sortByDescending { it.company }
        setData(newData)

    }

    fun showOriginalsList() {
        setData(orignalList)
    }

    private fun setData(newData: List<CompanyEntity>) {
        val diffResult = DiffUtil.calculateDiff(ClubDataListDiffCallback(list, newData))
        try {
            val updatedList = list.toMutableList()
            updatedList.clear()
            updatedList.addAll(newData)
            list = updatedList.toList()
            diffResult.dispatchUpdatesTo(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class ViewHolder(binding: ClubDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clubDataItemBinding: ClubDataItemBinding = binding
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            private lateinit var newList: List<CompanyEntity>

            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                val filteredList: MutableList<CompanyEntity> = ArrayList()
                if (charString.isNotEmpty()) {
                    for (data in orignalList) {
                        if (data.company!!.toLowerCase().contains(charSequence.toString())) {
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
                setData(filterResults.values as List<CompanyEntity>)
            }
        }
    }

}