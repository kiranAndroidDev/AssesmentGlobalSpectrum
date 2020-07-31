package com.example.assessmentspectrumglobal.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.example.assessmentspectrumglobal.databinding.ClubDataItemBinding


/**
Created by kiranb on 31/7/20
 */
class ClubDataListAdapter(var contex: Context, val list: List<ClubDataModel>) :
    RecyclerView.Adapter<ClubDataListAdapter.ViewHolder>() {
    private lateinit var binding: ClubDataItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ClubDataItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.clubDataItemBinding.root.setOnClickListener {  }
        holder.clubDataItemBinding.name = item.company
        holder.clubDataItemBinding.logo = item.logo
        holder.clubDataItemBinding.website = item.website
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(binding: ClubDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var clubDataItemBinding: ClubDataItemBinding

        init {
            clubDataItemBinding = binding
        }
    }

}