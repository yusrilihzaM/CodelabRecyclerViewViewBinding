package com.yusril.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusril.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var title = "Mode List"
    private val list = ArrayList<Hero>()// menampung data offline
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(title)
        // recycler view
        binding.rvHeroes.setHasFixedSize(true)
        list.addAll(getListHero())

        showRecylerViewList()
    }

    private fun showRecylerViewList() {
        binding.rvHeroes.layoutManager=LinearLayoutManager(this)
        val listHeroAdapter=ListHeroAdapter(list)// adapter dimasukan data dari list
        binding.rvHeroes.adapter=listHeroAdapter// set rvHeros ke adapternya
    }

    private fun showRecylerViewGrid() {
        binding.rvHeroes.layoutManager=GridLayoutManager(this,2)
        val gridLayoutManager=GridHeroAdapter(list)
        binding.rvHeroes.adapter=gridLayoutManager
    }
    private fun showRecylerViewCard() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        binding.rvHeroes.adapter = cardViewHeroAdapter
    }


    // ambil data dari String kemudian masukan ke array
    private fun getListHero(): ArrayList<Hero> {
        val listHero = ArrayList<Hero>()
        // ambil data darti string
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        // masukan ke list
        for(position in dataName.indices){
            val hero=Hero(
                    dataName[position],
                    dataDescription[position],
                    dataPhoto[position]
            )
            listHero.add(hero)
        }
        return listHero
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // inisilasisasi menu
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when(itemId){
            R.id.action_list -> {
                showRecylerViewList()
                title="Mode List"
            }
            R.id.action_grid -> {
                showRecylerViewGrid()
                title="Mode Grid"
            }
            R.id.action_cardview -> {
                showRecylerViewCard()
                title="Mode Card"
            }
        }
    }
}