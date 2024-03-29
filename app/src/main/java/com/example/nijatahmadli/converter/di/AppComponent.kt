package com.example.nijatahmadli.converter.di

import android.app.Application
import com.example.nijatahmadli.converter.ConverterApplication
import com.example.nijatahmadli.data.di.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        RetrofitModule::class
    ]
)
interface AppComponent : AndroidInjector<ConverterApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: ConverterApplication)
}