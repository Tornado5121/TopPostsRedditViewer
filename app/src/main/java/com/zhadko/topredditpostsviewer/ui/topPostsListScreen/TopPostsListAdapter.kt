package com.zhadko.topredditpostsviewer.ui.topPostsListScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhadko.topredditpostsviewer.databinding.TopPostAdapterItemBinding
import com.zhadko.topredditpostsviewer.models.domain.TopPostDomainModel

class TopPostsListAdapter(
    private val context: Context,
    private val onClick: (TopPostDomainModel) -> Unit,
) : ListAdapter<TopPostDomainModel,
        TopPostsListAdapter.TopPostViewHolder>(TopPostDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPostViewHolder {
        return TopPostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TopPostViewHolder, position: Int) {
        holder.bind(context, currentList[position], onClick)
    }

    class TopPostViewHolder(
        private val binding: TopPostAdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            context: Context,
            data: TopPostDomainModel,
            click: (TopPostDomainModel) -> Unit
        ) {
            with(binding) {
                authorFullName.text = data.author_fullname
                timeOfAdding.text = data.created.toString()
                numberComments.text = data.comments_number.toString()
                Glide.with(context).load(data.thumbnail_link).into(thumbnail)
                root.setOnClickListener {
                    click(data)
                }
            }
        }

        companion object {

            fun from(parent: ViewGroup): TopPostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopPostAdapterItemBinding.inflate(layoutInflater)
                return TopPostViewHolder(binding)
            }

        }

    }

    class TopPostDiffUtilCallback : DiffUtil.ItemCallback<TopPostDomainModel>() {

        override fun areItemsTheSame(
            oldItem: TopPostDomainModel,
            newItem: TopPostDomainModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TopPostDomainModel,
            newItem: TopPostDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

}