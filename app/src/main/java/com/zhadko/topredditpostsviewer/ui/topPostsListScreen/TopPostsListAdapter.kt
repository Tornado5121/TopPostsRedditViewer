package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhadko.topredditpostsviewer.databinding.TopPostAdapterItemBinding
import com.zhadko.topredditpostsviewer.models.TopPost

class TopPostsListAdapter(
    private val onClick: (TopPost) -> Unit,
    private val itemHasReached: () -> Unit
) : ListAdapter<TopPost,
        TopPostsListAdapter.TopPostViewHolder>(TopPostDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPostViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TopPostViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class TopPostViewHolder(binding: TopPostAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class TopPostDiffUtilCallback: DiffUtil.ItemCallback<TopPost>() {
        override fun areItemsTheSame(oldItem: TopPost, newItem: TopPost): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: TopPost, newItem: TopPost): Boolean {
            TODO("Not yet implemented")
        }

    }

}