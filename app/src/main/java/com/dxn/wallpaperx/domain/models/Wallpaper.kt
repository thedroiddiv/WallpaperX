package com.dxn.wallpaperx.domain.models

import android.os.Parcel
import android.os.Parcelable
data class Wallpaper(
    val id:String,
    val previewUrl: String,
    val smallUrl:String,
    val wallpaperUrl:String,
    val user: String,
    val userImageURL: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(previewUrl)
        parcel.writeString(smallUrl)
        parcel.writeString(wallpaperUrl)
        parcel.writeString(user)
        parcel.writeString(userImageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wallpaper> {
        override fun createFromParcel(parcel: Parcel): Wallpaper {
            return Wallpaper(parcel)
        }

        override fun newArray(size: Int): Array<Wallpaper?> {
            return arrayOfNulls(size)
        }
    }
}