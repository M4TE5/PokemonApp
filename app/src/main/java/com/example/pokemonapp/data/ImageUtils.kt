package com.example.pokemonapp.data

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

class ImageUtils(private val context: Context) {

    fun saveImageAndGetUri(imageName: String, bitmap: Bitmap){
        val directory = context.getDir("images", Context.MODE_PRIVATE)
        val file = File(directory, imageName)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        val imagePath = file.absolutePath // Этот путь можно сохранить в базе данных
    }

}