package com.zhadko.topredditpostsviewer.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Fragment.collectAsState(
    flow: Flow<T>,
    flowCollector: FlowCollector<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            flow.collect(flowCollector)
        }
    }
}