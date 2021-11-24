package com.preventor.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import com.preventor.pvtidentityverification.PreventorSDK
import com.preventor.pvtidentityverification.PreventorSDKListener
import com.preventor.pvtidentityverification.widgets.PreventorButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preventorSDK = PreventorSDK(this, this)
        val identityVerificationButton = findViewById<PreventorButton>(R.id.identityVerificationButton)

        val config = preventorSDK.getConfig()

        config.flowType = "YOUR_FLOW_TYPE"
        config.credentials.apiKey = "YOUR_API_KEY"
        config.credentials.clientSecret = "YOUR_CLIENT_SECRET"
        config.credentials.tenant = "YOUR_TENANT"
        config.credentials.banknu = "YOUR_BANKNU"
        config.credentials.env = "YOUR_ENV"

        config.currentUserInfo.cifCode = UUID.randomUUID().toString()


        config.theme.logo = R.drawable.ic_logotipo
        config.theme.button.backgroundColor = "YOUR_BACKGROUND_COLOR"
        config.theme.button.font.fontFamily = "YOUR_FONT_FAMILY"
        config.theme.button.font.fontSize = 14 //"YOUR_FONT_SIZE"
        config.theme.button.font.fontColor = "YOUR_FONT_COLOR"

        preventorSDK.setConfig(config)

        identityVerificationButton.apply()

        identityVerificationButton.setOnClickListener {
            preventorSDK.validateApiKey()
        }

        preventorSDK.callback(object : PreventorSDKListener {
            override fun onStart() {
                println("MainActivity onStart");
            }

            override fun onFinish() {
                println("MainActivity onFinish");
            }

            override fun onError(error: String) {
                println("MainActivity onError: $error");
            }

            override fun onSubmitted() {
                println("MainActivity onSubmitted");
            }
        })
    }
}
