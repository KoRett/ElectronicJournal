package com.gajeks.electronicjournal.models

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.gajeks.electronicjournal.databinding.PartLoadingBinding
import com.gajeks.electronicjournal.domain.models.ErrorResult
import com.gajeks.electronicjournal.domain.models.PendingResult
import com.gajeks.electronicjournal.domain.models.Result
import com.gajeks.electronicjournal.domain.models.SuccessResult

abstract class BaseFragment : Fragment() {

    abstract val vm: BaseViewModel

    fun <T> renderResult(
        root: ViewGroup, result: Result<T>,
        onPending: () -> Unit,
        onError: (Exception) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        root.children.forEach { it.visibility = View.GONE }
        when (result) {
            is SuccessResult -> onSuccess(result.data)
            is ErrorResult -> onError(result.exception)
            is PendingResult -> onPending()
            else -> {}
        }
    }

    fun <T> renderLoading(
        root: ViewGroup, result: Result<T>,
        onPending: () -> Unit,
        onError: (Exception) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        val loadingBinding = PartLoadingBinding.bind(root)
        when (result) {
            is SuccessResult -> {
                loadingBinding.fragmentLoading.visibility = View.GONE
                onSuccess(result.data)
            }
            is ErrorResult -> {
                loadingBinding.fragmentLoading.visibility = View.GONE
                onError(result.exception)
            }
            is PendingResult -> {
                loadingBinding.fragmentLoading.visibility = View.VISIBLE
                onPending()
            }
            else -> {}
        }
    }

}
