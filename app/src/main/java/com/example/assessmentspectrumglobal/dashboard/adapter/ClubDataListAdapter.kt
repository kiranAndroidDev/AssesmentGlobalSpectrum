package com.example.assessmentspectrumglobal.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.ClubDataItemBinding


/**
Created by kiranb on 31/7/20
 */
class ClubDataListAdapter(var list: List<ClubDataModel>, var listener: ItemClickListener) :
    RecyclerView.Adapter<ClubDataListAdapter.ViewHolder>() , Filterable{

    private var currentList: List<ClubDataModel>
    private lateinit var binding: ClubDataItemBinding

    init {
         currentList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ClubDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.clubDataItemBinding.root.setOnClickListener {
            listener.showMemberListOnItemSelect(item)
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
        val newData = ArrayList(currentList)
        if (ascending) {
            newData.sortBy { it.company }
        } else
            newData.sortByDescending { it.company }
        setData(newData)

    }

    fun showOriginalsList() {
        setData(list)
    }

    private fun setData(newData: List<ClubDataModel>) {
        val diffResult = DiffUtil.calculateDiff(ClubDataListDiffCallback(newData, currentList))
        val updatedList = currentList.toMutableList()
        updatedList.clear()
        updatedList.addAll(newData)
        currentList = updatedList.toList()
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: ClubDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clubDataItemBinding: ClubDataItemBinding = binding
    }

    interface ItemClickListener {
        fun showMemberListOnItemSelect(item: ClubDataModel)
    }


     override fun getFilter(): Filter {
         return object : Filter() {
             private lateinit var newList: List<ClubDataModel>

             override fun performFiltering(charSequence: CharSequence): FilterResults {
                 val charString = charSequence.toString()
                 val filteredList: MutableList<ClubDataModel> = ArrayList()
                 if (charString.isNotEmpty()) {
                     for (data in list) {
                         if (data.company!!.contains(charSequence.toString())) {
                             filteredList.add(data)
                         }
                     }
                     newList = filteredList
                 }else{
                     newList = list
                 }
                 val filterResults = FilterResults()
                 filterResults.values = filterResults
                 return filterResults
             }

             override fun publishResults(
                 charSequence: CharSequence,
                 filterResults: FilterResults
             ) {
                setData(filterResults.values as ArrayList<ClubDataModel>)
             }
         }
     }

}