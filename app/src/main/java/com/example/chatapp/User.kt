package com.example.chatapp

import android.R
import android.location.Address
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
var address: String="",
var email: String="",
var userId: String="",
var profileimgurl: String="",
var name: String="",
var password: String="",
var phone: String=""
) : Parcelable


