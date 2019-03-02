package com.sample.tikihometest.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.tikihometest.R
import com.sample.tikihometest.databinding.MainActivityBinding
import com.sample.tikihometest.util.observeEvent
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: MainActivityBinding
    private lateinit var mainViewModel: MainViewModel
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.apply {
            viewModel = mainViewModel
            setLifecycleOwner(this@MainActivity)
            keywordRecyclerView.apply {
                layoutManager = LinearLayoutManager(
                    this@MainActivity,
                    RecyclerView.HORIZONTAL,
                    false
                )
                adapter = KeywordItemAdapter(
                    mainViewModel = mainViewModel,
                    lifecycleOwner = this@MainActivity
                )
                addItemDecoration(KeywordItemDecoration(8))
            }
        }

        mainViewModel.run {
            errorEvent.observeEvent(this@MainActivity) {
                displayToast(it)
            }
        }
    }

    private fun displayToast(@StringRes id: Int) {
        val text = getString(id)
        toast?.cancel()
        toast = Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT)
        toast?.show()
    }
}
