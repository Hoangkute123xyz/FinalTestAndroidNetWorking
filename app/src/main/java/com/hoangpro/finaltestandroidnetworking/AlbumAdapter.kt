package com.hoangpro.finaltestandroidnetworking

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(context: Context) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    private val context = context
    private val albumList = ArrayList<Album>()
    private val orgAlbumList = ArrayList<Album>()
    private var search = ""

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tvTitle
        val tvInfo = itemView.tvInfo
        val imgStar = itemView.imgStar
        val parent = itemView.cardParent
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_album, parent, false))
    }

    override fun getItemCount(): Int = albumList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albumList[position]
        holder.apply {
            tvTitle.text = album.title
            tvInfo.text = "User Id: ${album.userId} - Id: ${album.id}"
            imgStar.visibility = if (album.id % 3 == 0) View.VISIBLE else View.GONE
            itemView.setOnClickListener {
                showDetail(album)
            }
        }
    }

    fun setData(albumList: ArrayList<Album>) {
        this.albumList.clear()
        this.orgAlbumList.clear()
        this.albumList.addAll(albumList)
        this.orgAlbumList.addAll(albumList)
        notifyDataSetChanged()
    }

    private fun showDetail(album: Album) {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_detail, null, false)
        builder.setView(view)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvInfo = view.findViewById<TextView>(R.id.tvInfo)
        val imgStar = view.findViewById<ImageView>(R.id.imgStar)
        tvTitle.text = album.title
        tvInfo.text = "User Id: ${album.userId} - Id: ${album.id}"
        imgStar.visibility = if (album.id % 3 == 0) View.VISIBLE else View.GONE
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun setSearch(query: String) {
        albumList.clear()
        search = query
        for (i in orgAlbumList.indices) {
            if (orgAlbumList[i].title.contains(query)) {
                albumList.add(orgAlbumList[i])
            }
        }
        notifyDataSetChanged()
    }
}