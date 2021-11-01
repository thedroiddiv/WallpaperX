package com.dxn.wallpaperx.common

sealed class Result<T>(val data: T?, val message:String?) {
    class Success<T>(data: T):Result<T>(data,null)
    class Failure<T>(message: String):Result<T>(null,message)
    class Loading<T>(message: String?):Result<T>(null,message)
}