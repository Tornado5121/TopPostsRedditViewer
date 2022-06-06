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
import kotlin.math.roundToInt

const val ITEM_PAGING = 1

class TopPostsListAdapter(
    private val context: Context,
    private val click: (TopPostDomainModel) -> Unit,
    private val itemHasReached: () -> Unit
) : ListAdapter<TopPostDomainModel,
        TopPostsListAdapter.TopPostViewHolder>(TopPostDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPostViewHolder {
        return TopPostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TopPostViewHolder, position: Int) {
        if (currentList[position].thumbnail_link.contains("https://", true)) {
            holder.bind(context, currentList[position], click)
        }
        if (position == itemCount - ITEM_PAGING) {
            itemHasReached()
        }
    }

    class TopPostViewHolder(
        private val binding: TopPostAdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            context: Context,
            data: TopPostDomainModel,
            click: (TopPostDomainModel) -> Unit
        ) {
            val authorFullNameData = data.author_fullname
            val hoursOfCreatedAgo =
                ((((System.currentTimeMillis() / 1000) - data.created) / (60 * 60)).roundToInt())
            val numberOfComments = data.comments_number.toString()

            val authorFullNameTextStringRes = "Posted by $authorFullNameData"
            val hoursOfCreateAgoStringRes = " $hoursOfCreatedAgo hours ago"
            val numberOfCommentsStringRes = "$numberOfComments comments"

            with(binding) {
                authorFullName.text = authorFullNameTextStringRes
                timeOfAdding.text = hoursOfCreateAgoStringRes
                numberComments.text = numberOfCommentsStringRes
                Glide.with(context).load(data.thumbnail_link).into(thumbnail)
                root.setOnClickListener {
                    click(data)
                }
            }
        }

        companion object {

            fun from(parent: ViewGroup): TopPostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopPostAdapterItemBinding.inflate(layoutInflater, parent, false)
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