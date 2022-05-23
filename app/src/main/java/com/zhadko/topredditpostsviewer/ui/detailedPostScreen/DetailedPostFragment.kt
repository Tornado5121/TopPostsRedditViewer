package com.zhadko.topredditpostsviewer.ui.detailedPostScreen

import android.os.Bundle
import androidx.fragment.app.Fragment

class DetailedPostFragment : Fragment() {


    companion object {

        private const val TOP_POST_ID = "top_post_id"
        fun getInstance(id: String): DetailedPostFragment {
            return DetailedPostFragment().apply {
                arguments = Bundle().apply {
                    putString(TOP_POST_ID, id)
                }
            }
        }
    }
}