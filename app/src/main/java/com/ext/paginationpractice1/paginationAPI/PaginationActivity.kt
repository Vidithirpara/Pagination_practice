package com.ext.paginationpractice1.paginationAPI

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.Utils.checkForInternet
import com.ext.paginationpractice1.databinding.ActivityPaginationBinding
import com.ext.paginationpractice1.util.Loader
import com.ext.paginationpractice1.util.Pagination1
import com.ext.paginationpractice1.util.PaginationAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaginationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaginationBinding
    private lateinit var adapterPaginationDummy: AdapterPaginationDummy
    private lateinit var dataList : ArrayList<Pagination1>
    var customLoader = Loader
    private var isLoading = false
    private var currentPage = 1
    private val limit = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaginationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        memoryAllocation()
    }

    private fun memoryAllocation(){
        dataList = ArrayList()
        adapterPaginationDummy = AdapterPaginationDummy(this, dataList)
        binding.rvDummy.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvDummy.adapter = adapterPaginationDummy

        customLoader.customLoader(this@PaginationActivity)

        setupScrollListener()
        getData(currentPage)

    }

    private fun getData(page: Int) {
        if (!checkForInternet(this)) {
            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show()
            return
        }

        isLoading = true

        if (page != 1) {
            showProgressBar()
        }

        if (page == 1){
            customLoader.showLoader()
        }

        val apiService = PaginationAPI.api.getData(page, limit)
        apiService.enqueue(object : Callback<List<Pagination1>> {
            override fun onResponse(call: Call<List<Pagination1>>, response: Response<List<Pagination1>>) {
                isLoading = false
                hideProgressBar()
                customLoader.hideLoader()
                if (response.isSuccessful && response.body() != null) {
                    val posts = response.body()!!
                    if (posts.isNotEmpty()) {
                        dataList.addAll(posts)
                        adapterPaginationDummy.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@PaginationActivity, "No more data to load.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PaginationActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Pagination1>>, t: Throwable) {
                isLoading = false
                hideProgressBar()
                customLoader.hideLoader()
                Toast.makeText(this@PaginationActivity, "Failed to fetch data.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupScrollListener() {
        binding.rvDummy.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && lastVisibleItemPosition + 1 >= totalItemCount) {
                    currentPage++
                    getData(currentPage)
                    Log.e("currentPage", currentPage.toString())
                }
            }
        })
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }

}