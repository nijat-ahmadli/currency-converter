package com.example.nijatahmadli.converter.presentation.converter.di.converterFragment

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.nijatahmadli.converter.presentation.converter.view.ConverterFragment
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.ConverterRecyclerViewAdapter
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.ConverterRecyclerViewAdapterImpl
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.factory.ConverterItemViewHolderFactory
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.viewHolder.factory.ConverterItemViewHolderFactoryImpl
import com.example.nijatahmadli.converter.presentation.converter.viewModel.ConverterViewModel
import com.example.nijatahmadli.converter.presentation.converter.viewModel.ConverterViewModelImpl
import com.example.nijatahmadli.converter.presentation.converter.viewModel.base.ItemViewModelFactory
import com.example.nijatahmadli.converter.presentation.converter.viewModel.factory.ConverterItemViewModelFactory
import com.example.nijatahmadli.converter.presentation.converter.viewModel.factory.ConverterViewModelFactory
import com.example.nijatahmadli.converter.presentation.di.scope.FragmentScope
import com.example.nijatahmadli.domain.model.Rate
import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
class ConverterFragmentModule {

    @Provides
    @FragmentScope
    fun provideBaseRate(): Rate {
        return Rate("EUR", BigDecimal(1.0))
    }

    @Provides
    @FragmentScope
    fun provideConverterViewModelFactory(
        factory: ConverterViewModelFactory
    ): ViewModelProvider.Factory {
        return factory
    }

    @Provides
    @FragmentScope
    fun provideConverterViewModel(
        fragment: ConverterFragment,
        viewModelFactory: ViewModelProvider.Factory
    ): ConverterViewModel {
        return ViewModelProviders.of(fragment, viewModelFactory)
            .get(ConverterViewModelImpl::class.java)
    }

    @Provides
    @FragmentScope
    fun provideConverterRecyclerViewAdapter(
        adapter: ConverterRecyclerViewAdapterImpl
    ): ConverterRecyclerViewAdapter {
        return adapter
    }

    @Provides
    @FragmentScope
    fun provideConverterItemViewHolderFactory(
        itemViewModelFactory: ItemViewModelFactory<Rate>,
        fragment: ConverterFragment
    ): ConverterItemViewHolderFactory {
        return ConverterItemViewHolderFactoryImpl(
            itemViewModelFactory,
            fragment::changeBase
        )
    }

    @Provides
    @FragmentScope
    fun provideConverterItemViewModelFactory(
        factory: ConverterItemViewModelFactory
    ): ItemViewModelFactory<Rate> {
        return factory
    }
}