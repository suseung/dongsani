package com.seungsu.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun createNewImageFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    )
}

fun getCurrentBitmap(
    context: Context,
    imagePath: String = "",
    imageUri: Uri = Uri.EMPTY
): Bitmap {
    val exifInterface = when {
        imagePath != "" -> ExifInterface(imagePath)
        else -> context.contentResolver.openInputStream(imageUri)?.let { ExifInterface(it) }
    }
    val bitmap = when {
        imagePath != "" -> BitmapFactory.decodeFile(imagePath)
        else -> MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
    }
    val orientation = exifInterface?.getAttributeInt(
        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL
    )
    val rotationDegrees = when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> 90f
        ExifInterface.ORIENTATION_ROTATE_180 -> 180f
        ExifInterface.ORIENTATION_ROTATE_270 -> 270f
        else -> 0f
    }
    return if (rotationDegrees != 0f) {
        val matrix = Matrix().apply { postRotate(rotationDegrees) }
        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    } else {
        bitmap
    }
}
