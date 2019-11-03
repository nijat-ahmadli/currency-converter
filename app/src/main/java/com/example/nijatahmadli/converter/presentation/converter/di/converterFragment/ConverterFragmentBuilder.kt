package com.example.nijatahmadli.converter.presentation.converter.di.converterFragment

import com.example.nijatahmadli.converter.presentation.converter.view.ConverterFragment
import com.example.nijatahmadli.converter.presentation.di.scope.FragmentScope
import com.example.nijatahmadli.data.di.ConverterDataModule
import com.example.nijatahmadli.domain.di.ConverterDomainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ConverterFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ConverterFragmentModule::class,
            ConverterDomainModule::class,
            ConverterDataModule::class
        ]
    )
    abstract fun bindConverterFragment(): ConverterFragment
}