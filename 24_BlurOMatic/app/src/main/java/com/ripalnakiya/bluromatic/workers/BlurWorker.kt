package com.ripalnakiya.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ripalnakiya.bluromatic.DELAY_TIME_MILLIS
import com.ripalnakiya.bluromatic.KEY_BLUR_LEVEL
import com.ripalnakiya.bluromatic.KEY_IMAGE_URI
import com.ripalnakiya.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds
import androidx.core.net.toUri
import androidx.work.workDataOf

private const val TAG = "BlurWorker"

class BlurWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)

        makeStatusNotification(applicationContext.resources.getString(R.string.blurring_image), applicationContext)

        return withContext(Dispatchers.IO) {
            try {
                delay(DELAY_TIME_MILLIS.milliseconds)
                require(!resourceUri.isNullOrBlank()) {
                    val errorMessage = applicationContext.resources.getString(R.string.invalid_input_uri)
                    Log.e(TAG, errorMessage)
                    errorMessage
                }

                val picture = BitmapFactory.decodeStream(
                    applicationContext.contentResolver.openInputStream(resourceUri.toUri())
                )

                val output = blurBitmap(picture, blurLevel)

                // Write bitmap to a temp file
                val outputUri = writeBitmapToFile(applicationContext, output)

                val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
                Result.success(outputData)
            } catch (throwable: Throwable) {
                Log.e(TAG, applicationContext.resources.getString(R.string.error_applying_blur), throwable)
                Result.failure()
            }
        }
    }

}