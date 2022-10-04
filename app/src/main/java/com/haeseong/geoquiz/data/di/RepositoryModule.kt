package com.haeseong.geoquiz.data.di

import com.haeseong.geoquiz.data.datasource.QuestionRemoteDataSource
import com.haeseong.geoquiz.data.QuestionRepositoryImpl
import com.haeseong.geoquiz.domain.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideQuestionRepository(
        questionRemoteDataSource: QuestionRemoteDataSource
    ): QuestionRepository = QuestionRepositoryImpl(questionRemoteDataSource)
}