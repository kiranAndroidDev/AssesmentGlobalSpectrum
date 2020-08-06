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

    describe("get club data test") {

            beforeEachTest {
                repo = mock()
                useCase = DashboardUseCase(repo)
            }

            afterEachTest {
                reset(repo)
            }

            describe("when getclub data called success") {
                context("When get club data returns list of companyWithMembers") {
                    it("return list successfully") {
                        runBlocking {
                            val clubDatamodel = CompanyWithMembers()
                            val list = mutableListOf<CompanyWithMembers>()
                            list.add(clubDatamodel)
                            `when`(repo.getClubDataRemote()).thenReturn(Resource.success(list))
                            val res = useCase.getClubData()
                            it("returns Success State"){
                                assertEquals(res, DashboardStates.CompanyWithMemberSuccess(list))
                            }

                            verify(repo).getClubDataRemote()
                            verifyNoMoreInteractions(repo)
                        }
                    }
                }


        }

        describe("when getclub data called error"){
            context("when get club data returns error"){
                runBlocking {
                    `when`(repo.getClubDataRemote()).thenReturn(Resource.error(""))
                    val res = useCase.getClubData()
                    assertEquals(res, DashboardStates.Error(""))
                    verify(repo).getClubDataRemote()
                    verifyNoMoreInteractions(repo)
                }
            }
        }
        }

    describe("get member data success"){
        beforeEachTest {
            repo = mock()
            useCase = DashboardUseCase(repo)
        }

        afterEachTest {
            reset(repo)
        }
        describe("when get member data called success") {
            context("When get member data returns list of Members") {
                it("return list successfully") {
                    runBlocking {
                        val clubDatamodel = MemberEntity(age = 20,cId = "",name = "", email = "", memberID = "", phone = "")
                        val list = mutableListOf<MemberEntity>()
                        list.add(clubDatamodel)
                        `when`(repo.loadMembers("")).thenReturn(Resource.success(list))
                        val res = useCase.loadMembers("")
                        it("returns Success State"){
                            assertEquals(res, DashboardStates.MemberDataSuccess(list))
                        }

                        verify(repo).loadMembers("")
                        verifyNoMoreInteractions(repo)
                    }
                }
            }

        }
        describe("when get member data called "){
            context("when get member data returns error"){
                runBlocking {
                    `when`(repo.getClubDataRemote()).thenReturn(Resource.error(""))
                    val res = useCase.loadMembers("")
                    assertEquals(res, DashboardStates.Error(""))
                    verify(repo).loadMembers("")
                    verifyNoMoreInteractions(repo)
                }
            }
        }
    }

})