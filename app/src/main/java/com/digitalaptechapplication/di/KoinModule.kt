package com.digitalaptechapplication.di

import com.digitalaptechapplication.database.AppDatabase
import com.digitalaptechapplication.feature.adduser.AddUserViewModel
import com.digitalaptechapplication.feature.dashboard.DashboardViewModel
import com.digitalaptechapplication.feature.homebase.HomeBaseActivity
import com.digitalaptechapplication.feature.homebase.HomeBaseViewModel
import com.digitalaptechapplication.feature.userlist.UserListViewModel
import com.digitalaptechapplication.repo.UserRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomModule = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().userDao() }
}

val repositoryModule = module {
    factory { UserRepo(get()) }
}

val viewModelModule = module {
    viewModel { HomeBaseViewModel() }
    viewModel { DashboardViewModel() }
    viewModel { UserListViewModel(get()) }
    viewModel { AddUserViewModel(get(), get()) }
}