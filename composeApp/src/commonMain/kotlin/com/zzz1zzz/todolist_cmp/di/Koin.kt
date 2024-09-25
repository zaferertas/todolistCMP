package com.zzz1zzz.todolist_cmp.di

import com.zzz1zzz.todolist_cmp.data.AppDatabase
import com.zzz1zzz.todolistcmp.data.Taskdata
import com.zzz1zzz.todolist_cmp.data.TaskDataSource
import com.zzz1zzz.todolist_cmp.data.TaskRepositoryImpl
import com.zzz1zzz.todolist_cmp.data.adapter.InstantSqlDelightAdapter
import com.zzz1zzz.todolist_cmp.domain.DateTimeService
import com.zzz1zzz.todolist_cmp.domain.TaskRepository
import com.zzz1zzz.todolist_cmp.viewModel.AddTaskViewModel
import com.zzz1zzz.todolist_cmp.viewModel.MainViewModel
import com.zzz1zzz.todolist_cmp.viewModel.TaskDetailsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule, platformModule)
    }

@Suppress("unused") // Called from Swift
fun initKoin() = initKoin() {}

val commonModule = module {
    single {
        AppDatabase(
            driver = get(),
            TaskdataAdapter = Taskdata.Adapter(
                createdAtAdapter = InstantSqlDelightAdapter,
                completedAtAdapter = InstantSqlDelightAdapter,
            )
        )
    }
    factoryOf(::MainViewModel)
    factoryOf(::AddTaskViewModel)
    factoryOf(::TaskDetailsViewModel)
    singleOf(::TaskDataSource)
    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
    singleOf(::DateTimeService)
}

expect val platformModule: Module