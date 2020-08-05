package com.example.assessmentspectrumglobal.dashboard.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.assessmentspectrumglobal.database.CompanyEntity
import com.example.assessmentspectrumglobal.database.CompanyWithMembers


/**
Created by kiranb on 31/7/20
 */
class ClubDataListDiffCallback(
    oldList: List<CompanyEntity>,
    newList: List<CompanyEntity>
) : DiffUtil.Callback() {
    private val oldList: List<CompanyEntity>
    private val newList: List<CompanyEntity>
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