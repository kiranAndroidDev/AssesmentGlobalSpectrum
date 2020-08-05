package com.example.assessmentspectrumglobal.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
Created by kiranb on 4/8/20
 */
@Dao
abstract class CompanyWithMembersDao {
    public fun insert(companyWithMembers: CompanyWithMembers) {
        companyWithMembers.companyEntity?.let {
            insertCompany(it);
            companyWithMembers.members?.forEach { i ->
                run {
                    i.cId = companyWithMembers.companyEntity!!.id

                }
            }
            insertAll(companyWithMembers.members!!);
        }
    }


    @Insert
    abstract fun insertAll(members: List<MemberEntity>);

    @Insert
    abstract fun insertCompany(companyEntity: CompanyEntity); //return type is the key here.

    @Update
    abstract fun updateCompany(companyEntity: CompanyEntity)

    @Update
    abstract fun updateMember(memberEntity: MemberEntity)

    @Transaction
    @Delete
    abstract fun delete(companyEntity: CompanyEntity, members: List<MemberEntity>);

    @Transaction
    @Query("SELECT * FROM company")
    public abstract fun loadAll(): List<CompanyWithMembers>;
}


