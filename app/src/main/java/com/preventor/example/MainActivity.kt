package com.preventor.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.preventor.pvtidentityverification.PreventorSDK
import com.preventor.pvtidentityverification.PreventorSDKListener
import com.preventor.pvtidentityverification.model.Ticked
import com.preventor.pvtidentityverification.widgets.PreventorButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preventorSDK = PreventorSDK(this, this)
        val config = preventorSDK.getConfig()

        config.flowType = "YOUR_FLOW_ID"
        config.credentials.apiKey = "YOUR_API_KEY"
        config.credentials.clientSecret = "YOUR_CLIENT_SECRET"
        config.credentials.tenant = "YOUR_TENANT"
        config.credentials.banknu = "YOUR_BANKNU"
        config.credentials.env = "YOUR_ENV"
        config.currentUserInfo.cifCode = "YOUR_CIFCODE"


        val identityVerificationButton = findViewById<PreventorButton>(R.id.identityVerificationButton)

        preventorSDK.initialize()

        identityVerificationButton.setOnClickListener {
            preventorSDK.validateApiKey()
        }

        preventorSDK.callback(object : PreventorSDKListener {
            override fun onStart() {
                println("MainActivity onStart")
            }

            override fun onFinish(ticked: Ticked) {
                println("MainActivity onFinish")
            }

            override fun onError(error: String) {
                println("MainActivity onError: $error")
            }

            override fun onSubmitted(ticked: Ticked) {
                println("MainActivity onSubmitted")
            }

        })
    }

}
