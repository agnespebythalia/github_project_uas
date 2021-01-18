package com.github.api.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize

data class User (
   val login : String,
   val avatar_url : String,
   val html_url : String,
   val followers_url : String,
   val followers : Int,
   val following_url : String,
   val following : Int,
   val repos_url : String,
   val repos : Int
) : Parcelable