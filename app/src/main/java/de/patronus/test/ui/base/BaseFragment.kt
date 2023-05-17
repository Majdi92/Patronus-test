package de.patronus.test.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import de.patronus.test.presentation.viewmodel.BaseViewModel
import de.patronus.test.ui.core.dialog.dismissLoadingDialog
import de.patronus.test.ui.core.dialog.showLoadingDialog
import de.patronus.test.ui.extension.showSnackBar

abstract class BaseFragment<VB : ViewBinding, ViewModel : BaseViewModel> : Fragment() {
    protected lateinit var binding: VB
    protected abstract val viewModel: ViewModel

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected open fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoadingDialog() else dismissLoadingDialog()
    }

    protected open fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        dismissLoadingDialog()
        Log.e(  "handleErrorMessage: ", message.toString())

        showSnackBar(binding.root, message)
    }
}