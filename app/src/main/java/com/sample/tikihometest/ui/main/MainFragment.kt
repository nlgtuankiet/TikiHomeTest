package com.sample.tikihometest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.MvRxViewModelStore
import com.sample.tikihometest.databinding.MainFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState

class MainFragment : DaggerFragment(), MvRxView {
    override val mvrxViewModelStore: MvRxViewModelStore by lazy { MvRxViewModelStore(viewModelStore) }

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory
    private lateinit var binding: MainFragmentBinding
    private val mainViewModel: MainViewModel by fragmentViewModel()
    private var toast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvrxViewModelStore.restoreViewModels(this, savedInstanceState)
    }
    private lateinit var keywordItemsController: KeywordItemController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = mainViewModel
            setLifecycleOwner(viewLifecycleOwner)

            keywordRecyclerView.apply {
                keywordItemsController = KeywordItemController()
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.HORIZONTAL,
                    false
                )
                adapter = keywordItemsController.adapter
                addItemDecoration(KeywordItemDecoration(8))
            }
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvrxViewModelStore.saveViewModels(outState)
    }

    override fun onStart() {
        super.onStart()
        // This ensures that invalidate() is called for static screens that don't
        // subscribe to a ViewModel.
        postInvalidate()
    }

    override fun invalidate() {
        withState(mainViewModel) {
            binding.state = it
            keywordItemsController.setData(it.keywordItems())
            it.errorEvent?.getContentIfNotHandled()?.let { messageId ->
                displayToast(messageId)
            }
        }
    }

    private fun displayToast(@StringRes id: Int) {
        val text = getString(id)
        toast?.cancel()
        toast = Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT)
        toast?.show()
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}