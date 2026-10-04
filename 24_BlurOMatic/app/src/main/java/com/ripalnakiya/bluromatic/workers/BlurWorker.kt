package com.ripalnakiya.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ripalnakiya.bluromatic.DELAY_TIME_MILLIS
import com.ripalnakiya.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "BlurWorker"

class BlurWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        makeStatusNotification(applicationContext.resources.getString(R.string.blurring_image), applicationContext)

        return withContext(Dispatchers.IO) {
            try {
                delay(DELAY_TIME_MILLIS.milliseconds)
                val picture = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.android_cupcake)

                val output = blurBitmap(picture, 1)

                // Write bitmap to a temp file
                val outputUri = writeBitmapToFile(applicationContext, output)

                makeStatusNotification("Output is $outputUri", applicationContext)

                Result.success()
            } catch (throwable: Throwable) {
                Log.e(TAG, applicationContext.resources.getString(R.string.error_applying_blur), throwable)
                Result.failure()
            }
        }
    }

}