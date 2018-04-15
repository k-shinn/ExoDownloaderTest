package com.kei.exoplayertrial.playList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kei.exoplayertrial.databinding.PlayListRowBinding

class PlayListAdapter(var list: List<MediaData>) : RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlayListRowBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = list[position].title
        holder.binding.drmType.text = list[position].drmType
        holder.binding.url.text = list[position].url
        holder.binding.title.setOnClickListener {
            listener.onClick(it, list[position])
        }
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onClick(view: View, data: MediaData)
    }

    class ViewHolder(var binding: PlayListRowBinding) : RecyclerView.ViewHolder(binding.root)
}