package com.zzz1zzz.todolist_cmp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.zzz1zzz.todolist_cmp.data.AppDatabase
import org.koin.dsl.module
import java.io.File

actual val platformModule = module {
    single<SqlDriver> {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
            AppDatabase.Schema.create(it)
        }
//        val isDebug = true
//        val parentFolder = if (isDebug) {
//            File(System.getProperty("java.io.tmpdir"))
//        } else {
//            File(System.getProperty("user.home") + "/TodoListApp")
//        }
//        if (!parentFolder.exists()) {
//            parentFolder.mkdirs()
//        }
//        val databasePath = if (isDebug) {
//            File(System.getProperty("java.io.tmpdir"), "todolist.db")
//        } else {
//            File(parentFolder, "todolist.db")
//        }
//        JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}").also { driver ->
//            AppDatabase.Schema.create(driver = driver)
//        }
    }
}