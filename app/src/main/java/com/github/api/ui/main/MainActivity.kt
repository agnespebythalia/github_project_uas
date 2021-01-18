package com.github.api.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.api.DetailActivity
import com.github.api.R
import com.github.api.adapter.MainAdapter
import com.github.api.data.User
import com.github.api.data.UserDummy
import com.github.api.databinding.ActivityMainBinding
import com.github.api.ui.detail.DetailUserActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also{
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        binding.apply {
            recycleMain.layoutManager = LinearLayoutManager(this@MainActivity)
            recycleMain.setHasFixedSize(true)
            recycleMain.adapter = adapter


        }
        viewModel.getSearchUsers().observe(this,{
            if (it!=null){
                adapter.setList(it)
                showLoading(false)
            }
        })
        recycleMain.adapter = adapter
        recycleMain.layoutManager = LinearLayoutManager(this)
        recycleMain.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                    viewModel.setSearchUsers(query)
                    showLoading(true)

                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })



        return super.onCreateOptionsMenu(menu)
    }








    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE

        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}