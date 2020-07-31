package com.example.assessmentspectrumglobal.dashboard.adapter

import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.ClubDataItemBinding


/**
Created by kiranb on 31/7/20
 */
class ClubDataListAdapter(var list: ArrayList<ClubDataModel>) :
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
        holder.clubDataItemBinding.root.setOnClickListener { }
        holder.clubDataItemBinding.name = item.company
        holder.clubDataItemBinding.logo = item.logo
        holder.clubDataItemBinding.website = item.website
    }

    override fun getItemCount(): Int {
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

}