package com.ext.paginationpractice1.paginationAPIwithImage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.Utils.checkForInternet
import com.ext.paginationpractice1.databinding.ActivityPaginationApiwithImageBinding
import com.ext.paginationpractice1.util.Loader
import com.ext.paginationpractice1.util.PaginationAPIWithImages
import com.ext.paginationpractice1.util.User
import com.ext.paginationpractice1.util.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaginationAPIWithImageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPaginationApiwithImageBinding
    private lateinit var adapterPaginationWithImage: AdapterPaginationWithImage
    private lateinit var dataList : ArrayList<User>
    var customLoader = Loader
    private var isLoading = false
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaginationApiwithImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        memoryAllocation()

    }

    private fun memoryAllocation(){
        dataList = ArrayList()
        adapterPaginationWithImage = AdapterPaginationWithImage(this, dataList)
        binding.rvUsers.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvUsers.adapter = adapterPaginationWithImage

        customLoader.customLoader(this@PaginationAPIWithImageActivity)

        setupScrollListener()
        getUser(currentPage)
    }

    private fun getUser(page: Int) {
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

        val apiService = PaginationAPIWithImages.api2.getUsers(page)
        apiService.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                isLoading = false
                hideProgressBar()
                customLoader.hideLoader()

                if (response.isSuccessful && response.body() != null) {
                    val userResponse = response.body()!!
                    if (userResponse.data.isNotEmpty()) {
                        dataList.addAll(userResponse.data)
                        adapterPaginationWithImage.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@PaginationAPIWithImageActivity, "No more data to load.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PaginationAPIWithImageActivity, "Some error occurred.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                isLoading = false
                hideProgressBar()
                customLoader.hideLoader()
                Toast.makeText(this@PaginationAPIWithImageActivity, "Failed to fetch data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupScrollListener() {
        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && lastVisibleItemPosition + 1 >= totalItemCount) {
                    currentPage++
                    getUser(currentPage)
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