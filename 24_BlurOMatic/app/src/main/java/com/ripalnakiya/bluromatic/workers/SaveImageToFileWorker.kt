package com.ripalnakiya.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.ripalnakiya.bluromatic.DELAY_TIME_MILLIS
import com.ripalnakiya.bluromatic.KEY_IMAGE_URI
import com.ripalnakiya.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds

/**
 * Saves the image to a permanent file
 */
private const val TAG = "SaveImageToFileWorker"

class SaveImageToFileWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    private val title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z", Locale.getDefault())

    override suspend fun doWork(): Result {
        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification(applicationContext.resources.getString(R.string.saving_image), applicationContext)

        return withContext(Dispatchers.IO) {
            delay(DELAY_TIME_MILLIS.milliseconds)

            return@withContext try {
                val resourceUri = inputData.getString(KEY_IMAGE_URI)

                val bitmap = BitmapFactory.decodeStream(
                    applicationContext.contentResolver.openInputStream(Uri.parse(resourceUri))
                )

                val imageUrl = MediaStore.Images.Media.insertImage(
                    applicationContext.contentResolver, bitmap, title, dateFormatter.format(Date())
                )

                if (!imageUrl.isNullOrEmpty()) {
                    val output = workDataOf(KEY_IMAGE_URI to imageUrl)
                    Result.success(output)
                } else {
                    Log.e(TAG, applicationContext.resources.getString(R.string.writing_to_mediaStore_failed))
                    Result.failure()
                }
            } catch (exception: Exception) {
                Log.e(TAG, applicationContext.resources.getString(R.string.error_saving_image), exception)
                Result.failure()
            }
        }
    }
}