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
        setupClickListeners()
        initViews()
    }

    protected open fun setupObservers() {}
    protected open fun setupClickListeners() {}
    protected open fun initViews() {}

    protected fun <T> LiveData<Resource<T>>.resourceHandler(
        isLoading: (Boolean) -> Unit,
        onSuccess: (T?) -> Unit,
        onError: ((String?) -> Unit)? = null
    ) {
        this.observe(viewLifecycleOwner) { resource ->
            isLoading(resource is Resource.Loading)
            when (resource) {
                is Resource.Error -> {
                    showToast(resource.message)
                    onError?.invoke(resource.message)
                }
                is Resource.Success -> onSuccess(resource.data)
                is Resource.Loading -> {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}