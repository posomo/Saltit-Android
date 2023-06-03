package com.posomo.saltit.common_ui.base

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : Fragment() {

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this@BaseFragment
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView()

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }

    protected inline fun bind(block: T.() -> Unit) {
        binding.apply(block)
    }

    protected fun getColor(colorInt: Int, theme: Resources.Theme? = null)
        = ResourcesCompat.getColor(resources, colorInt, theme)

    protected fun launchOnLifecycleStarted(block: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED, block)
        }
    }
}