package com.example.assessmentspectrumglobal.dashboard.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel

/**
Created by kiranb on 31/7/20
 */
class ClubDataListDiffCallback(
    oldList: List<ClubDataModel>,
    newList: List<ClubDataModel>
) : DiffUtil.Callback() {
    private val oldList: List<ClubDataModel>
    private val newList: List<ClubDataModel>
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == (newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    init {
        this.oldList = oldList
        this.newList = newList
    }
}