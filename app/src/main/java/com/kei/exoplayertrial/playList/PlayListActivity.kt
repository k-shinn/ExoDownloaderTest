package com.kei.exoplayertrial.playList

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kei.exoplayertrial.R
import com.kei.exoplayertrial.databinding.ActivityPlayListBinding

class PlayListActivity : AppCompatActivity() {

    private val binding: ActivityPlayListBinding by lazy {
        DataBindingUtil.setContentView<ActivityPlayListBinding>(this, R.layout.activity_play_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list = arrayListOf<MediaData>().apply {
            add(MediaData("title1", "drm1", "url1"))
            add(MediaData("title2", "drm2", "url2"))
        }

        val adapter = PlayListAdapter(list).apply {
            setOnClickListener(object : PlayListAdapter.OnItemClickListener {
                override fun onClick(view: View, data: MediaData) {
                    Snackbar.make(view, "clickData:" + data.title, Snackbar.LENGTH_LONG)
                            .setAction("Action", null)
                            .show()
                }
            })
        }

        binding.playList.setHasFixedSize(true)
        binding.playList.layoutManager = LinearLayoutManager(this)
        binding.playList.adapter = adapter
    }
}
