package com.mitroz.woloologin.application_kotlin.presentation.activities.login

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.URLSpan
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


import com.mitroz.woloologin.R
import com.mitroz.woloologin.application_kotlin.AppConstants
import com.mitroz.woloologin.databinding.ActivityLoginV2Binding
import `in`.woloo.www.application_kotlin.model.server_request.SendOtpRequest
import `in`.woloo.www.application_kotlin.model.server_response.SendOtpResponse
import com.mitroz.woloologin.application_kotlin.view_models.WolooViewModel


import com.mitroz.woloologin.application_kotlin.view_models.HomeViewModel
import `in`.woloo.www.application_kotlin.model.lists_models.LocaleRequest

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : AppCompatActivity() {

    private lateinit var wolooViewModel: WolooViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: ActivityLoginV2Binding



    private val CREDENTIAL_PICKER_REQUEST = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginV2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        wolooViewModel = ViewModelProvider(this)[WolooViewModel::class.java]
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]



        //splashPresenter.getAuthConfig();
        val request = LocaleRequest.Locale()
        request.packageName = "in.woloo.www"
        request.platform = "android"

        val localeRequest = LocaleRequest()
        localeRequest.locale = request

        homeViewModel.getAppConfig(localeRequest)



        val textPT = "Please read our Terms & Conditions and our Privacy Policy"
        val spannable = SpannableString(textPT)

// Adding clickable links using URLSpan
        val start1 = textPT.indexOf("Terms & Conditions")
        val end1 = start1 + "Terms & Conditions".length
        spannable.setSpan(URLSpan("https://woloo.in/terms-condition/"), start1, end1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val start2 = textPT.indexOf("Privacy Policy")
        val end2 = start2 + "Privacy Policy".length
        spannable.setSpan(URLSpan("https://woloo.in/privacy-policy/"), start2, end2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

// Changing link color with ForegroundColorSpan
        spannable.setSpan(ForegroundColorSpan(getColor(R.color.chip_color)), start1, end1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(getColor(R.color.chip_color)), start2, end2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.privacyTermsText.text = spannable
        binding.privacyTermsText.movementMethod = LinkMovementMethod.getInstance()


        setLiveData()
        addTextWatchers()
        setClickables()
        generateKeyHash()
        //requestHint()
    }

    private fun addTextWatchers() {
        binding.etEmailMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (cs?.length!! >= 1) {
                   // binding.etEmailMobile.setBackgroundResource(R.drawable.rounded_corner_button)
                    enableSubmitButton()
                } else {
                   // binding.etEmailMobile.setBackgroundResource(R.drawable.rounded_white)
                    disableSubmitButton()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun generateKeyHash() {
        Handler(Looper.getMainLooper()).postDelayed({ keyhash() }, 5000)
    }

/*    private fun requestHint() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()
        val credentialsClient = Credentials.getClient(this)
        val intent = credentialsClient.getHintPickerIntent(hintRequest)
        startIntentSenderForResult(
            intent.intentSender,
            CREDENTIAL_PICKER_REQUEST,
            null, 0, 0, 0
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CREDENTIAL_PICKER_REQUEST ->
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val credential = data.getParcelableExtra<Credential>(Credential.EXTRA_KEY)
                    if (!TextUtils.isEmpty(credential?.id)) {
                        binding.etEmailMobile.setText(credential?.id?.removePrefix("+91"))
                    }
                }
        }
    }*/


    private fun enableSubmitButton() {
        try {
            //binding.txtSendOtp.background = resources.getDrawable(R.drawable.yellow_rectangle_shape)
          //  binding.txtSendOtp.setTextColor(resources.getColor(R.color.black))
        } catch (ex: java.lang.Exception) {

        }
    }

    private fun disableSubmitButton() {
        try {
         //   binding.txtSendOtp.background = resources.getDrawable(R.drawable.rounded_corner_button)
           // binding.txtSendOtp.setTextColor(resources.getColor(R.color.text_color_five))
        } catch (ex: java.lang.Exception) {

        }
    }



    private fun keyhash() {
        val info: PackageInfo
        try {
            info = this.packageManager
                .getPackageInfo("in.woloo.www", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))

            }
        } catch (e1: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        } catch (e: Exception) {

        }
    }

    private fun setClickables() {
        binding.txtSendOtp.setOnClickListener {
           binding.txtSendOtp.background = ContextCompat.getDrawable(applicationContext , R.drawable.new_button_onclick_background)
            sendOtp()
        }

       /* binding.tvPrivacyPolicy.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra("privacy_policy","https://woloo.in/privacy-policy/") // Changed By Aarati
            //intent.putExtra("privacy_policy","https://api.woloo.in/WolooTermsofUse.html")
            startActivity(intent)
        }*/
    }

    private fun sendOtp() {



                    val bundle = Bundle()

                    val request = SendOtpRequest(binding.etEmailMobile.text.toString().trim() , "SharedPrefSettings.getPreferences.fetchReferralCode().toString()")
                   /* if(SharedPrefSettings.getPreferences.fetchReferralCode()!= null) {
                        request.referralCode = SharedPrefSettings.getPreferences.fetchReferralCode().toString()
                    }
                    request.mobile = binding.etEmailMobile.text.toString().trim { it <= ' ' }*/
                    wolooViewModel.sendOtp(request)



    }

    private fun setLiveData() {
       /* homeViewModel.observeAppConfig().observe(this) {
            if (it != null) {
                getPreferences.storeAuthConfig(it)
            }
        }*/
       wolooViewModel.observeSendOtp().observe(this, Observer {
           Toast.makeText(this , "Go to OTP screen" + it.success , Toast.LENGTH_LONG)
           if(it != null){
                Toast.makeText(this , "Go to OTP screen" + it.success , Toast.LENGTH_LONG)
              //  goToOtpVerification(it.data)
            }
        })
    }



    private fun goToOtpVerification(data: SendOtpResponse?) {

      //  if (data == null) return
      //  finish()
    }

    private fun showLoginFailureDialog(msg: String?) {
        try {
         Toast.makeText(this , "Failed" , Toast.LENGTH_LONG)
        } catch (e: java.lang.Exception) {

        }
    }



}