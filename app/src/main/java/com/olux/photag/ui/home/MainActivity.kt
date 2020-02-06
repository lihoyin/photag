package com.olux.photag.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.olux.photag.R
import com.olux.photag.models.Photo
import com.olux.photag.repositories.ApiClient
import com.olux.photag.repositories.MyCallback
import com.olux.photag.ui.editPhoto.EditPhotoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ApiClient.photoService.getPhotos()
            .enqueue(MyCallback<List<Photo>> {
                Log.d("test", "photo list size: " + it.size)
            })

        fab.setOnClickListener { startActivity(Intent(this, EditPhotoActivity::class.java)) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
