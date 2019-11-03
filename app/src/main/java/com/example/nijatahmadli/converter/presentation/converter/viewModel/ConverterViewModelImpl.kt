package com.example.nijatahmadli.converter.presentation.converter.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.nijatahmadli.common.DataResult
import com.example.nijatahmadli.converter.presentation.converter.viewModel.base.BaseViewModel
import com.example.nijatahmadli.domain.model.Currencies
import com.example.nijatahmadli.domain.model.Rate
import com.example.nijatahmadli.domain.usecase.GetCurrenciesUseCase
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ConverterViewModelImpl(
    baseRate: Rate,
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : BaseViewModel(), ConverterViewModel {

    companion object {
        const val REQUEST_PERIOD: Long = 1
    }

    override var baseCurrencyCode = baseRate.code
    override val rates = MutableLiveData<List<Rate>>()
    override val showLoading = MutableLiveData<Boolean>()
    override val showError = MutableLiveData<String>()

    override fun getCurrencies() {
        showLoading.postValue(true)
        Observable.interval(REQUEST_PERIOD, TimeUnit.SECONDS, Schedulers.io())
            .flatMap { getCurrenciesUseCase(baseCurrencyCode) }
            .subscribe(::handleGetCurrenciesResult)
            .addTo(compositeDisposable)
    }

    private fun handleGetCurrenciesResult(dataResult: DataResult<Currencies>) {
        showLoading.postValue(false)
        when (dataResult) {
            is DataResult.Success -> handleSuccess(dataResult.value)
            is DataResult.Failure -> handleFailure(dataResult.throwable)
        }
    }

    private fun handleSuccess(currencies: Currencies) {
        rates.postValue(currencies.rates)
    }

    private fun handleFailure(throwable: Throwable) {
        showError.postValue(throwable.message)
    }
}