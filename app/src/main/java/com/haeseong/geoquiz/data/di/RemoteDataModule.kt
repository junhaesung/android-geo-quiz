package com.haeseong.geoquiz.data.di

import com.haeseong.geoquiz.data.QuestionApi
import com.haeseong.geoquiz.data.datasource.QuestionRemoteDataSource
import com.haeseong.geoquiz.data.datasource.QuestionRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideQuestionRemoteDataSource(questionApi: QuestionApi): QuestionRemoteDataSource =
        QuestionRemoteDataSourceImpl(questionApi)
}