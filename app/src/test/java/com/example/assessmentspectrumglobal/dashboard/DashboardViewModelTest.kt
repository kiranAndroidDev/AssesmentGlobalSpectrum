package com.example.assessmentspectrumglobal.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.utils.Resource
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

/**
Created by kiranb on 10/8/20
 */
@RunWith(JUnitPlatform::class)
@ExperimentalCoroutinesApi
class DashboardViewModelTest : Spek({
    lateinit var useCase: DashboardContract.ILogic
    lateinit var repo: DashboardContract.IRepo
    lateinit var dashboardViewModel: DashboardViewModel
    lateinit var dashboardStateObserver: Observer<DashboardStates>
    lateinit var testDispatcher: TestCoroutineDispatcher

    describe("dashboard test data changes") {
        beforeGroup {
            repo = mock()
            dashboardStateObserver = mock()
            testDispatcher = TestCoroutineDispatcher()
            useCase = DashboardUseCase(repo)
            dashboardViewModel = DashboardViewModel(useCase)
        }
        afterGroup {
            reset(repo)
            reset(dashboardStateObserver)
        }
        /*
        * testing with flow of group -> test -> it
        * */
        group("getClubData") {
            val clubDatamodel = CompanyWithMembers()
            val list = mutableListOf<CompanyWithMembers>()
            list.add(clubDatamodel)
            test("get club data success") {
                it("should return CompanyWithMemberSuccess state") {
                    runBlocking(testDispatcher) {
                        Mockito.`when`(repo.getClubDataRemote())
                            .thenReturn(Resource.success(list))
                        dashboardViewModel.getClubData()
                        dashboardViewModel.subscribeToState()
                            ?.observeForever(dashboardStateObserver)

                        verify(dashboardStateObserver).onChanged(DashboardStates.Loading)
                        verify(dashboardStateObserver).onChanged(
                            DashboardStates.CompanyWithMemberSuccess(
                                list
                            )
                        )
                    }
                }
            }
            test("get club data error") {
                it("should return error state") {
                    runBlocking(testDispatcher) {
                        Mockito.`when`(repo.getClubDataRemote())
                            .thenReturn(Resource.error(null))
                        dashboardViewModel.getClubData()
                        dashboardViewModel.subscribeToState()
                            ?.observeForever(dashboardStateObserver)

                        verify(dashboardStateObserver).onChanged(DashboardStates.Loading)
                        verify(dashboardStateObserver).onChanged(
                            DashboardStates.Error(null)
                        )

                    }
                }
            }
        }

        group("load all members of a specific company") {
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
            val successRespone = Resource.success(list)
            val errorResponse = Resource.error<MutableList<MemberEntity>>(null)
            test("get member list success") {
                it("should return success state") {
                    runBlocking(testDispatcher) {
                        Mockito.`when`(repo.loadMembers("1")).thenReturn(successRespone)
                        dashboardViewModel.loadMembers("1")

                            dashboardViewModel.subscribeToState()
                                ?.observeForever(dashboardStateObserver)

                            verify(
                                dashboardStateObserver,
                                times(1)
                            ).onChanged(DashboardStates.Loading)
                            verify(dashboardStateObserver, times(1)).onChanged(
                                DashboardStates.MemberDataSuccess(
                                    list
                                )
                            )
                    }
                }
            }
            test("get member list error") {
                it("should return error state") {
                    runBlocking(testDispatcher) {
                        Mockito.`when`(repo.loadMembers("1")).thenReturn(errorResponse)
                        dashboardViewModel.loadMembers("1")

                            dashboardViewModel.subscribeToState()
                                ?.observeForever(dashboardStateObserver)
                            verify(dashboardStateObserver).onChanged(DashboardStates.Loading)
                            verify(dashboardStateObserver).onChanged(
                                DashboardStates.Error(null)
                            )
                        }
                    }
            }
        }
    }
})
