package com.example.assessmentspectrumglobal.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.assessmentspectrumglobal.database.CompanyWithMembers
import com.example.assessmentspectrumglobal.database.MemberEntity
import com.example.assessmentspectrumglobal.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.on
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
    val testDispatcher = TestCoroutineDispatcher()

    describe("dashboard test data changes") {
        beforeEachTest {
            repo = mock()
            dashboardStateObserver = mock()
            useCase = DashboardUseCase(repo)
            dashboardViewModel = DashboardViewModel(useCase)
        }
        afterEachTest {
            reset(repo)
        }
        on("get club data success") {
            context("get club data return list of companies") {
                runBlocking(testDispatcher) {
                    val clubDatamodel = CompanyWithMembers()
                    val list = mutableListOf<CompanyWithMembers>()
                    list.add(clubDatamodel)
                    Mockito.`when`(repo.getClubDataRemote()).thenReturn(Resource.success(list))
                    dashboardViewModel.getClubData()
                    dashboardViewModel.subscribeToState()?.observeForever(dashboardStateObserver)

                    verify(dashboardStateObserver).onChanged(DashboardStates.Loading)
                    verify(dashboardStateObserver).onChanged(
                        DashboardStates.CompanyWithMemberSuccess(
                            list
                        )
                    )
                }
            }
        }
        on("get club data error") {
            context("get club data return error") {
                runBlocking(testDispatcher) {
                    Mockito.`when`(repo.getClubDataRemote()).thenReturn(Resource.error(null))
                    dashboardViewModel.getClubData()
                    dashboardViewModel.subscribeToState()?.observeForever(dashboardStateObserver)

                    verify(dashboardStateObserver).onChanged(DashboardStates.Loading)
                    verify(dashboardStateObserver).onChanged(
                        DashboardStates.Error(null)
                    )
                }
            }
        }
        on("get member list success") {
            context("get club data return list of companies") {
                runBlocking(testDispatcher) {
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
                    Mockito.`when`(repo.loadMembers("1")).thenReturn(Resource.success(list))
                    dashboardViewModel.loadMembers("1")
                    dashboardViewModel.subscribeToState()?.observeForever(dashboardStateObserver)

                    verify(dashboardStateObserver).onChanged(DashboardStates.Loading)
                    verify(dashboardStateObserver).onChanged(
                        DashboardStates.MemberDataSuccess(
                            list
                        )
                    )
                }
            }
        }
        on("get member list error") {
            context("get club data return error") {
                runBlocking(testDispatcher) {
                    Mockito.`when`(repo.loadMembers("1")).thenReturn(Resource.error(null))
                    dashboardViewModel.loadMembers("1")
                    dashboardViewModel.subscribeToState()?.observeForever(dashboardStateObserver)

                    verify(dashboardStateObserver).onChanged(DashboardStates.Loading)
                    verify(dashboardStateObserver).onChanged(
                        DashboardStates.Error(null)
                    )
                }
            }
        }

    }
})
