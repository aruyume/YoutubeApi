package com.example.youtubeapi.ui.fragment.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.youtubeapi.utils.Resource
import com.example.youtubeapi.utils.showToast

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    protected open fun setupObservers() {}

    protected fun <T> LiveData<Resource<T>>.resourceHandler(
        onSuccess: (data: T) -> Unit,
        state: (Resource<T>) -> Unit
    ) {
        this.observe(viewLifecycleOwner) { resource ->
            state(resource)
            when (resource) {
                is Resource.Error -> showToast(resource.message ?: "Unknown error")
                is Resource.Success -> resource.data?.let { onSuccess(it) }
                is Resource.Loading -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}