package com.zzz1zzz.todolist_cmp.data.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant

object InstantSqlDelightAdapter : ColumnAdapter<Instant, Long> {

    override fun decode(databaseValue: Long): Instant = Instant.fromEpochMilliseconds(databaseValue)

    override fun encode(value: Instant): Long = value.toEpochMilliseconds()
}
