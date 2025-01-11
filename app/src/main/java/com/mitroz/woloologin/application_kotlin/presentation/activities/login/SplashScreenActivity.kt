package com.mitroz.woloologin.application_kotlin.presentation.activities.login


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mitroz.woloologin.R
import com.mitroz.woloologin.databinding.ActivityLoginV2Binding
import com.mitroz.woloologin.databinding.ActivitySplashBinding


class SplashScreenActivity :
    AppCompatActivity()
{

    private lateinit var binding: ActivitySplashBinding

    @RequiresApi(api = Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initView() {


            binding?.tvNext?.setOnClickListener {
                binding?.tvNext?.background = ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.new_button_onclick_background
                )

                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }



    }


    companion object {
        private val TAG = SplashScreenActivity::class.java.simpleName
    }
}