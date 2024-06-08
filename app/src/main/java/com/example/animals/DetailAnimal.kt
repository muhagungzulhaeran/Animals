package com.example.animals

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ShareActionProvider
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat

class DetailAnimal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_animal)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Detail Animal"

        val dataAnimal = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("extra_animal", Animal::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("extra_animal")
        }

        val imgAnimal:ImageView = findViewById(R.id.animal_image_detail)
        val nameAnimal:TextView = findViewById(R.id.animal_name)
        val descAnimal:TextView = findViewById(R.id.description_isi)
        val habitatAnimal:TextView = findViewById(R.id.tv_isi_habitat)
        val masaHidupAnimal: TextView = findViewById(R.id.tv_isi_masaHidup)

        if (dataAnimal != null) {
            imgAnimal.setImageResource(dataAnimal.photo)
            nameAnimal.text = dataAnimal.name
            descAnimal.text = dataAnimal.description
            habitatAnimal.text = dataAnimal.habitat
            masaHidupAnimal.text = dataAnimal.masaHidup
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                shareApp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareApp() {
        val appmsg :String = "Hey !, Check out this app for share button on App Bar" +
                "https://play.google.com/store/apps/details?id=com.example.animals"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, appmsg)
        intent.type = "text/plain"
        startActivity(intent)
    }
}