package com.example.catwork.di

import com.example.catwork.data.db.ToDoDatabase
import com.example.catwork.data.repository.ToDoItemRepository
import com.example.catwork.data.repository.ToDoItemRepositoryImpl
import com.example.catwork.domain.usecase.AddToDoUseCase
import com.example.catwork.domain.usecase.DeleteToDoUseCase
import com.example.catwork.domain.usecase.GetToDoListUseCase
import com.example.catwork.domain.usecase.UpdateToDoUseCase
import com.example.catwork.presentation.home.HomeContract
import com.example.catwork.presentation.home.HomeFragment
import com.example.catwork.presentation.home.HomePresenter
import com.example.catwork.presentation.mypage.CalendarContract
import com.example.catwork.presentation.mypage.CalendarFragment
import com.example.catwork.presentation.mypage.CalendarPresenter
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    single { ToDoDatabase.build(androidApplication())}
    single { get<ToDoDatabase>().toDoDao() }

    // Repository
    single<ToDoItemRepository> { ToDoItemRepositoryImpl(get(), get()) }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { AddToDoUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }
    factory { DeleteToDoUseCase(get()) }

    // Presentation
    scope<HomeFragment> {
        scoped<HomeContract.Presenter> { HomePresenter(get(), get(), get(), get(), get()) }
    }

    scope<CalendarFragment> {
        scoped<CalendarContract.Presenter> { CalendarPresenter(get()) }
    }
}