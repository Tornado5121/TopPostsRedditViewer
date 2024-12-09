package com.zhadko.topredditpostsviewer.utils

object MyAuthRes {

    const val AUTH_URL = "https://www.reddit.com/api/v1/authorize.compact?client_id=%s" +
            "&response_type=code&state=%s&redirect_uri=%s&" +
            "duration=permanent&scope=identity"

    const val CLIENT_ID = "4NPE_rIBjWRDCOpuaH4POQ"

    const val REDIRECT_URI = "http://www.example.com/my_redirect"

    const val STATE = "MY_RANDOM_STRING_1"

}