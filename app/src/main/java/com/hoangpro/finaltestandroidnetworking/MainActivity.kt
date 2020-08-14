package com.hoangpro.finaltestandroidnetworking

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        initData()
        swipeToRefresh.setOnRefreshListener {
            initData()
        }
    }

    private fun initData() {
        progressDialog.show()
        swipeToRefresh.isRefreshing=true
        Client.getAllAlbums({
            if (it!=null){
                albumAdapter.setData(it)
            }
            progressDialog.dismiss()
            swipeToRefresh.isRefreshing=false
        },{
            progressDialog.dismiss()
            swipeToRefresh.isRefreshing=false
        })

        edtSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                albumAdapter.setSearch(s.toString())
            }

        })
    }

    private fun setupView() {
        albumAdapter = AlbumAdapter(this)
        rvAlbum.apply {
            adapter=albumAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
    }
}