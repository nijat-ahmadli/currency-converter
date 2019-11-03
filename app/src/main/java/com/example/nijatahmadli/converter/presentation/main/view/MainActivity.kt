package com.example.nijatahmadli.converter.presentation.main.view

import android.os.Bundle
import com.example.nijatahmadli.converter.R
import com.example.nijatahmadli.converter.presentation.base.BaseActivity
import com.example.nijatahmadli.converter.presentation.converter.view.ConverterFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ConverterFragment.newInstance())
                .commitNow()
        }
    }
}