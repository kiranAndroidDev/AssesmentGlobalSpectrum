package com.example.assessmentspectrumglobal.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.MemberDataItemBinding

/**
Created by kiranb on 2/8/20
 */
class MemberListAdapter (val list: List<ClubDataModel.Member>) : RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {

    private var currentList: List<ClubDataModel.Member> = list

    private lateinit var binding: MemberDataItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MemberDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.memberDataItemBinding.item = item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun showSortedListByName(ascending: Boolean) {
        val newData = ArrayList(list)
        if (ascending) {
            newData.sortBy { it.name?.first }
        } else
            newData.sortByDescending { it.name?.first }
        setData(newData)

    }



    fun showSortedListByAge(ascending: Boolean) {
        val newData = ArrayList(currentList)
        if (ascending) {
            newData.sortBy { it.age }
        } else
            newData.sortByDescending { it.age }
        setData(newData)

    }

    fun showOrignalList() {
        setData(currentList)
    }

    private fun setData(newData: List<ClubDataModel.Member>) {
        val diffResult = DiffUtil.calculateDiff(MemberListDiffCallback(newData, currentList))
        val updatedList = currentList.toMutableList()
        updatedList.clear()
        updatedList.addAll(newData)
        currentList = updatedList.toList()
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: MemberDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var memberDataItemBinding: MemberDataItemBinding = binding
    }
}