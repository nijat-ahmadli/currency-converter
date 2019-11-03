package com.example.nijatahmadli.domain.di

import com.example.nijatahmadli.converter.presentation.di.scope.FragmentScope
import com.example.nijatahmadli.domain.usecase.GetCurrenciesUseCase
import com.example.nijatahmadli.domain.usecase.GetCurrenciesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class ConverterDomainModule {

    @Provides
    @FragmentScope
    fun provideGetCurrenciesUseCase(useCase: GetCurrenciesUseCaseImpl): GetCurrenciesUseCase {
        return useCase
    }
}