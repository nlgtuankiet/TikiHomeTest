package com.sample.tikihometest.ui.main

import android.os.Bundle
import com.sample.tikihometest.R
import com.sample.tikihometest.util.inTransaction
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                add(R.id.fragment_container, MainFragment.newInstance())
            }
        }
    }
}
