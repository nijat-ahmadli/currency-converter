package com.example.nijatahmadli.converter.di

import com.example.nijatahmadli.converter.presentation.converter.di.converterFragment.ConverterFragmentBuilder
import com.example.nijatahmadli.converter.presentation.di.scope.ActivityScope
import com.example.nijatahmadli.converter.presentation.main.di.MainActivityModule
import com.example.nijatahmadli.converter.presentation.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            ConverterFragmentBuilder::class]
    )
    abstract fun bindMainActivity(): MainActivity
}