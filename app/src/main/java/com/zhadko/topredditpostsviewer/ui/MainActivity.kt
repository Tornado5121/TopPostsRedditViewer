package com.zhadko.topredditpostsviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhadko.topredditpostsviewer.R
import com.zhadko.topredditpostsviewer.ui.topPostsListScreen.TopPostsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.my_fragment_container, TopPostsListFragment())
                .commit()
        }
    }

}