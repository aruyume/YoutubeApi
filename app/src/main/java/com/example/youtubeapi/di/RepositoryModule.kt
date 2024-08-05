package com.example.youtubeapi.di

import com.example.youtubeapi.data.repositories.YoutubeRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    factory {
        YoutubeRepository(get())
    }
}