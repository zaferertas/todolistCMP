package com.zzz1zzz.todolist_cmp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.zzz1zzz.todolist_cmp.data.AppDatabase
import com.zzz1zzz.todolist_cmp.viewModel.AddTaskViewModel
import com.zzz1zzz.todolist_cmp.viewModel.MainViewModel
import com.zzz1zzz.todolist_cmp.viewModel.TaskDetailsViewModel
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getMainViewModel() = getKoin().get<MainViewModel>()
    fun getAddTaskViewModel() = getKoin().get<AddTaskViewModel>()
    fun getTaskDetailsViewModel() = getKoin().get<TaskDetailsViewModel>()
}

actual val platformModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            AppDatabase.Schema,
            "todolist.db"
        )
    }
}
