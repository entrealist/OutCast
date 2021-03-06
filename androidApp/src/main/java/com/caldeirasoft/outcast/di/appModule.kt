package com.caldeirasoft.outcast.di

import com.caldeirasoft.outcast.Database
import com.caldeirasoft.outcast.data.db.createDatabase
import com.caldeirasoft.outcast.data.repository.EpisodeRepositoryImpl
import com.caldeirasoft.outcast.data.repository.InboxRepositoryImpl
import com.caldeirasoft.outcast.data.repository.PodcastRepositoryImpl
import com.caldeirasoft.outcast.data.repository.QueueRepositoryImpl
import com.caldeirasoft.outcast.domain.repository.EpisodeRepository
import com.caldeirasoft.outcast.domain.repository.InboxRepository
import com.caldeirasoft.outcast.domain.repository.PodcastRepository
import com.caldeirasoft.outcast.domain.repository.QueueRepository
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module


@ExperimentalCoroutinesApi
@FlowPreview
internal val appModule = module {
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, get(), "outCastDb.db") }
    single { createDatabase(get()) }

    single<PodcastRepository> { PodcastRepositoryImpl(database = get()) }
    single<EpisodeRepository> { EpisodeRepositoryImpl(database = get()) }
    single<InboxRepository> { InboxRepositoryImpl(database = get()) }
    single<QueueRepository> { QueueRepositoryImpl(database = get()) }

}
