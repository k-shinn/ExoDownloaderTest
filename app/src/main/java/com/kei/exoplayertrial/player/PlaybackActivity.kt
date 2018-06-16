package com.kei.exoplayertrial.player

import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import com.google.android.exoplayer2.offline.Downloader
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper
import com.google.android.exoplayer2.source.dash.manifest.RepresentationKey
import com.google.android.exoplayer2.source.dash.offline.DashDownloader
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.kei.exoplayertrial.R
import com.kei.exoplayertrial.R.id.url
import java.io.File

class PlaybackActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)
    }

    override fun onResume() {
        super.onResume()

        val filesDirs = getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS)
        val url = "https://storage.googleapis.com/wvmedia/clear/hevc/tears/tears.mpd"
//        download(file.toString(), url)
        val task = MyAsyncTask(filesDirs[0], url)
        task.execute()

    }

    public fun download(folderPath: String, manifestUrl: String) {
        val downloadFolder = File(folderPath)
        val simpleCache = SimpleCache(downloadFolder, NoOpCacheEvictor())
        val factory = DefaultHttpDataSourceFactory("ExoPlayer", null)

        val helper = DownloaderConstructorHelper(simpleCache, factory)

        val manifestUrl = Uri.parse(manifestUrl)
        val dashDownloader = DashDownloader(manifestUrl, helper)
//        val key = RepresentationKey(0, 0, 0)
//        val value: Any = RepresentationKey[]{ RepresentationKey(0, 0, 0) }
        dashDownloader.selectRepresentations(null)

        dashDownloader.download({ _, downloadPercentage, downloadedBytes ->
            Log.d("downloadPercentage", downloadPercentage.toString())
            Log.d("downloadedBytes", downloadedBytes.toString())
            // todo
        })
        val cacheDataSource = CacheDataSource(simpleCache, factory.createDataSource(), CacheDataSource.FLAG_BLOCK_ON_CACHE)

    }

    open class MyAsyncTask(val file: File, val url: String) : AsyncTask<Void, Void, String>() {


        override fun doInBackground(vararg params: Void): String? {
            download(file.toString(), url)

            return null
        }

        private fun download(folderPath: String, manifestUrl: String) {
            val downloadFolder = File(folderPath)
            val simpleCache = SimpleCache(downloadFolder, NoOpCacheEvictor())
            val factory = DefaultHttpDataSourceFactory("ExoPlayer", null)

            val helper = DownloaderConstructorHelper(simpleCache, factory)

            val manifestUrl = Uri.parse(manifestUrl)
            val dashDownloader = DashDownloader(manifestUrl, helper)
//        val key = RepresentationKey(0, 0, 0)
//        val value: Any = RepresentationKey[]{ RepresentationKey(0, 0, 0) }
            dashDownloader.selectRepresentations(null)

            dashDownloader.download({ _, downloadPercentage, downloadedBytes ->
                Log.d("downloadPercentage", downloadPercentage.toString())
                Log.d("downloadedBytes", downloadedBytes.toString())
                // todo
            })
            val cacheDataSource = CacheDataSource(simpleCache, factory.createDataSource(), CacheDataSource.FLAG_BLOCK_ON_CACHE)

        }


    }
}
