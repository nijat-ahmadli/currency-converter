package com.example.nijatahmadli.converter.presentation.converter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nijatahmadli.converter.R
import com.example.nijatahmadli.converter.databinding.ConverterFragmentBinding
import com.example.nijatahmadli.converter.presentation.base.BaseFragment
import com.example.nijatahmadli.converter.presentation.base.observeLiveData
import com.example.nijatahmadli.converter.presentation.converter.view.adapter.ConverterRecyclerViewAdapter
import com.example.nijatahmadli.converter.presentation.converter.viewModel.ConverterViewModel
import javax.inject.Inject

class ConverterFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            ConverterFragment()
    }

    private lateinit var binding: ConverterFragmentBinding
    private var recyclerView: RecyclerView? = null

    @Inject
    lateinit var adapter: ConverterRecyclerViewAdapter
    @Inject
    lateinit var viewModel: ConverterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater, container)
        observeRates()
        observeError()
        setUpRecyclerView()
        viewModel.getCurrencies()
        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.converter_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    private fun observeRates() {
        viewModel.rates.observeLiveData(this) {
            with(adapter) {
                setRates(it)
            }
        }
    }

    private fun observeError() {
        viewModel.showError.observeLiveData(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
        }
    }

    private fun setUpRecyclerView() {
        recyclerView =
            binding.root.findViewById<RecyclerView>(R.id.converter_recycler_view)?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@ConverterFragment.adapter
            }
    }

    fun changeBase(position: Int) {
        viewModel.baseCurrencyCode = adapter.currencyCodes[position]
        adapter.setBase(position)
        recyclerView?.scrollToPosition(0)
    }
}