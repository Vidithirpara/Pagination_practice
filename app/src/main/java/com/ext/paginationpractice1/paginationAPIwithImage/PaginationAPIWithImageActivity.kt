package com.ext.paginationpractice1.paginationAPIwithImage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.paginationpractice1.R
import com.ext.paginationpractice1.databinding.ActivityPaginationApiwithImageBinding

class PaginationAPIWithImageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPaginationApiwithImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaginationApiwithImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}