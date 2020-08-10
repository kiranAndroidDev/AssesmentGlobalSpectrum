package com.example.assessmentspectrumglobal.dashboard

import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`


/**
 * Created by kiranb on 6/8/20
 */
@RunWith(JUnitPlatform::class)
class DashboardUseCaseTest : Spek({
    lateinit var useCase: DashboardContract.ILogic
    lateinit var repo: DashboardContract.IRepo

    describe("get data from repo") {

        beforeEachTest {
            repo = mock()
            useCase = DashboardUseCase(repo)
        }

        afterEachTest {
            reset(repo)
        }

        on("when get club data  success") {
            context("When get club data returns list of companyWithMembers") {
                runBlocking {
                    val clubDatamodel = CompanyWithMembers()
                    val list = mutableListOf<CompanyWithMembers>()
                    list.add(clubDatamodel)
                    `when`(repo.getClubDataRemote()).thenReturn(Resource.success(list))
                    val res = useCase.getClubData()
                    it("returns Success State") {
                        assertEquals(res, DashboardStates.CompanyWithMemberSuccess(list))
                    }

                    verify(repo).getClubDataRemote()
                    verifyNoMoreInteractions(repo)
                }
            }


        }
        on("when club data get failure") {
            context("when get club data returns error") {
                runBlocking {
                    `when`(repo.getClubDataRemote()).thenReturn(Resource.error(null))
                    val res = useCase.getClubData()
                    it("returns error State") {
                        assertEquals(res, DashboardStates.Error(null))
                    }

                    verify(repo).getClubDataRemote()
                    verifyNoMoreInteractions(repo)
                }
            }

        }


        on("when member data called success") {
            context("When it returns list of members") {
                runBlocking {
                    val memberList = MemberEntity(
                        cId = "1",
                        age = 20,
                        memberID = "",
                        email = "",
                        phone = "",
                        favourite = false,
                        name = ""
                    )
                    val list = mutableListOf<MemberEntity>()
                    list.add(memberList)
                    `when`(repo.loadMembers("1")).thenReturn(Resource.success(list))
                    val res = useCase.loadMembers("1")
                    it("returns Success State") {
                        assertEquals(res, DashboardStates.MemberDataSuccess(list))
                    }

                    verify(repo).loadMembers("1")
                    verifyNoMoreInteractions(repo)
                }

            }

        }

        on("when member data get failure") {
            context("when get member list data returns error") {
                runBlocking {
                    `when`(repo.loadMembers("1")).thenReturn(Resource.error(null))
                    val res = useCase.loadMembers("1")
                    it("returns error State") {
                        assertEquals(res, DashboardStates.Error(null))
                    }

                    verify(repo).loadMembers("1")
                    verifyNoMoreInteractions(repo)
                }
            }

        }

    }
})