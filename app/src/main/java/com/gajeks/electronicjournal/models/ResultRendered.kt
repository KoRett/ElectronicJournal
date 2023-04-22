package com.gajeks.electronicjournal.models

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.gajeks.electronicjournal.R
import com.gajeks.electronicjournal.databinding.PartResultBinding
import com.gajeks.electronicjournal.domain.models.Result

fun <T> BaseFragment.renderedSimpleResult(
    root: ViewGroup,
    result: Result<T>,
    onSuccess: (T) -> Unit
) {
    val binding = PartResultBinding.bind(root)
    renderResult(
        root = root,
        result = result,
        onPending = {
            binding.progressBar.visibility = View.VISIBLE
        },
        onError = {
            binding.errorContainer.visibility = View.VISIBLE
        },
        onSuccess = { successData ->
            root.children.filter { it.id != R.id.progress_bar && it.id != R.id.error_container }
                .forEach { it.visibility = View.VISIBLE }
            onSuccess(successData)
        }
    )
}