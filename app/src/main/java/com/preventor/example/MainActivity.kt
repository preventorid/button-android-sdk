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

        config.credentials.apiKey = "FB0uWMb3Pk9hMuiTk8QZu9Y5Uvewfc2f8k0Fv1Md"
        config.credentials.clientSecret = "ff8f777a82800ff67de016ddf8bd0b3b0fecc305f0431f10a08730e0fe9d87eb"
        config.credentials.tenant = "preventor"
        config.credentials.banknu = "1"
        config.credentials.env = "playground"


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
