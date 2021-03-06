package com.zhadko.topredditpostsviewer.models

data class TokenResponse(
    var access_token: String,
    var token_type: String,
    var expires_in: String,
    var scope: String,
    var refresh_token: String
)