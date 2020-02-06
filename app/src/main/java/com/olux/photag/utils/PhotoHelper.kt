package com.olux.photag.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PhotoHelper {
    const val MAX_WIDTH = 1000

    fun getBitmap(context: Context, uri: Uri): File {
        val file = File(context.cacheDir, uri.lastPathSegment)
        try {
            var bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            if (bitmap.width > MAX_WIDTH) {
                bitmap = Bitmap.createScaledBitmap(
                    bitmap,
                    MAX_WIDTH,
                    bitmap.height * MAX_WIDTH / bitmap.width,
                    true
                )
            }

            file.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val bitmapData = bos.toByteArray()

            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file
    }
}