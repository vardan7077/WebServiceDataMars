package com.example.marswebservicecontact.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MarsClass(
    val id: String,
    val img_src: String,
    val type: String,
    val price: Double):Parcelable{
    val isRental
        get() = type == "rent"
}