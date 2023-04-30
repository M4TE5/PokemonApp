package com.example.pokemonapp.data.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import java.io.File
import java.io.FileOutputStream

class ImageUtils(private val context: Context) {
    fun saveImageAndGetUri(imageName: String, url: String): String{
        val bitmap = getBitmap(url)
        val directory = context.getDir(DIR_NAME, Context.MODE_PRIVATE)
        val file = File(directory, imageName + FILE_FORMAT)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return file.absolutePath
    }

    private fun getBitmap(url: String): Bitmap {
        val futureTarget: FutureTarget<Bitmap> = Glide.with(context)
            .asBitmap()
            .load(url)
            .submit()
        return futureTarget.get()
    }

    companion object{
        const val DIR_NAME = "images"
        const val FILE_FORMAT = ".png"
    }
}