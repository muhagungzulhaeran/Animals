package com.example.animals

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ANIMAL = "extra_animal"
    }

    private lateinit var rvAnimals: RecyclerView
    private val list = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimals = findViewById(R.id.rv_animals)
        rvAnimals.setHasFixedSize(true)

        list.addAll(getListAnimals())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, About::class.java)
                startActivity(moveIntent)
            }
            R.id.action_list -> {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    rvAnimals.layoutManager = GridLayoutManager(this, 2)
                } else {
                    rvAnimals.layoutManager = LinearLayoutManager(this)
                }
            }
            R.id.action_grid -> {
                rvAnimals.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListAnimals(): ArrayList<Animal> {
        val dataName =  resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataHabitat = resources.getStringArray(R.array.data_habitat)
        val dataMasaHidup = resources.getStringArray(R.array.data_masaHidup)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAnimal = ArrayList<Animal>()
        for (i in dataName.indices) {
            val animal = Animal(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataHabitat[i], dataMasaHidup[i])
            listAnimal.add(animal)
        }
        return listAnimal
    }

    private fun showRecyclerList() {
        rvAnimals.layoutManager = LinearLayoutManager(this)
        val listAnimalAdapter = ListAnimalAdapter(list)
        rvAnimals.adapter = listAnimalAdapter

        listAnimalAdapter.setOnItemClickCallback(object : ListAnimalAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Animal) {
                showSelectedAnimal(data)
            }
        })
    }

    private fun showSelectedAnimal(animal: Animal){
        val intentToDetail = Intent(this@MainActivity, DetailAnimal::class.java)
        intentToDetail.putExtra(EXTRA_ANIMAL, animal)
        startActivity(intentToDetail)
    }
}