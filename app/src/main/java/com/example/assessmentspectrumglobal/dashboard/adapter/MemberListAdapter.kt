package com.example.assessmentspectrumglobal.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.ClubDataItemBinding
import com.example.assessmentspectrumglobal.databinding.MemberDataItemBinding

/**
Created by kiranb on 2/8/20
 */
class MemberListAdapter (var list: ArrayList<ClubDataModel.Member>) : RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {

    private lateinit var binding: MemberDataItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MemberDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
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
        val newData = ArrayList(list)
        if (ascending) {
            newData.sortBy { it.age }
        } else
            newData.sortByDescending { it.age }
        setData(newData)

    }

    fun showOrignalList() {
        setData(list)
    }

    private fun setData(newData: ArrayList<ClubDataModel.Member>) {
        val diffResult = DiffUtil.calculateDiff(MemberListDiffCallback(newData, list))
        list.clear()
        this.list.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: MemberDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var memberDataItemBinding: MemberDataItemBinding = binding
    }
}