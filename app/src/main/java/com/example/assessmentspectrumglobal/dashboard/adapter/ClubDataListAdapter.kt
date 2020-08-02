package com.example.assessmentspectrumglobal.dashboard.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.ClubDataItemBinding


/**
Created by kiranb on 31/7/20
 */
class ClubDataListAdapter(val list: ArrayList<ClubDataModel>, var listener:ItemClickListener) :
    RecyclerView.Adapter<ClubDataListAdapter.ViewHolder>() {

    private lateinit var binding: ClubDataItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ClubDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.clubDataItemBinding.row.setOnClickListener {
            listener.showMemberListOnItemSelect(item)
        }
        holder.clubDataItemBinding.item = item

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        Log.e("size", "${list.size}")
        return list.size
    }

    fun showSortedList(ascending: Boolean) {
        val newData = ArrayList(list)
        if (ascending) {
            newData.sortBy { it.company }
        } else
            newData.sortByDescending { it.company }
        setData(newData)

    }
    fun showOrignalList(){
        setData(list)
    }

    private fun setData(newData: ArrayList<ClubDataModel>) {
        val diffResult = DiffUtil.calculateDiff(ClubDataListDiffCallback(newData, list))
        list.clear()
        this.list.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: ClubDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clubDataItemBinding: ClubDataItemBinding = binding
    }

    interface ItemClickListener{
        fun showMemberListOnItemSelect(item: ClubDataModel)
    }


   /* override fun getFilter(): Filter {
        return object : Filter() {
            private lateinit var newList: ArrayList<ClubDataModel>

            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                val filteredList: MutableList<ClubDataModel> = ArrayList()
                if (charString.isNotEmpty()) {
                    for (data in list) {
                        if (data.company!!.contains(charSequence.toString())) {
                            filteredList.add(data)
                        }
                    }
                    newList = filteredList as ArrayList<ClubDataModel>
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
    }*/

}