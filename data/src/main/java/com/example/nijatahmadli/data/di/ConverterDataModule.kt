package com.example.nijatahmadli.data.di

import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.common.mapper.Mapper
import com.example.nijatahmadli.common.mapper.MapperWithNullableInput
import com.example.nijatahmadli.converter.presentation.di.scope.FragmentScope
import com.example.nijatahmadli.data.datasource.remote.RemoteDataSource
import com.example.nijatahmadli.data.datasource.remote.RemoteDataSourceImpl
import com.example.nijatahmadli.data.datasource.remote.api.CurrenciesApi
import com.example.nijatahmadli.data.datasource.remote.mapper.NetworkToDomainCurrenciesMapper
import com.example.nijatahmadli.data.datasource.remote.mapper.NetworkToDomainRatesMapper
import com.example.nijatahmadli.data.datasource.remote.model.NetworkCurrencies
import com.example.nijatahmadli.data.repository.CurrenciesRepositoryImpl
import com.example.nijatahmadli.data.repository.mapper.DataResultMapper
import com.example.nijatahmadli.data.repository.mapper.DataResultMapperImpl
import com.example.nijatahmadli.data.repository.mapper.NetworkToDomainDataResultMapper
import com.example.nijatahmadli.domain.model.Currencies
import com.example.nijatahmadli.domain.model.Rate
import com.example.nijatahmadli.domain.repository.CurrenciesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.math.BigDecimal

@Module
class ConverterDataModule {

    @Provides
    @FragmentScope
    fun provideCurrenciesApi(retrofit: Retrofit): CurrenciesApi {
        return retrofit.create(CurrenciesApi::class.java)
    }

    @Provides
    @FragmentScope
    fun provideDataResultMapper(mapper: DataResultMapperImpl): DataResultMapper {
        return mapper
    }

    @Provides
    @FragmentScope
    fun provideRemoteDataSource(dataSource: RemoteDataSourceImpl): RemoteDataSource {
        return dataSource
    }

    @Provides
    @FragmentScope
    fun provideNetworkToDomainCurrenciesMapper(
        mapper: NetworkToDomainCurrenciesMapper
    ): Mapper<NetworkCurrencies, Currencies> {
        return mapper
    }

    @Provides
    @FragmentScope
    fun provideNetworkToDomainCurrenciesDataResultMapper(
        mapper: NetworkToDomainDataResultMapper<NetworkCurrencies, Currencies>
    ): Mapper<DataResult<NetworkCurrencies>, DataResult<Currencies>> {
        return mapper
    }

    @Provides
    @FragmentScope
    fun provideCurrenciesRepository(repository: CurrenciesRepositoryImpl): CurrenciesRepository {
        return repository
    }

    @Provides
    @FragmentScope
    fun provideNetworkToDomainRatesMapper(
        mapper: NetworkToDomainRatesMapper
    ): MapperWithNullableInput<Map<String, BigDecimal>, List<Rate>> {
        return mapper
    }
}