package com.example.retrofit_coroutines_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.User

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerview:RecyclerView
    lateinit var myProgressBar:ProgressBar

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }



    private fun setupUI() {

        myRecyclerview =findViewById(R.id.myRecyclerview)

        myProgressBar = findViewById(R.id.myProgressBar)



        myRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        myRecyclerview.addItemDecoration(
            DividerItemDecoration(
                myRecyclerview.context,
                (myRecyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        myRecyclerview.adapter = adapter
    }


    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        myRecyclerview.visibility = View.VISIBLE
                        myProgressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        myRecyclerview.visibility = View.VISIBLE
                        myProgressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        myProgressBar.visibility = View.VISIBLE
                        myRecyclerview.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users as ArrayList<User>)
            notifyDataSetChanged()
        }
    }
}