package com.preventor.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preventorSDK = PreventorSDK(this, this)
        val identityVerificationButton = findViewById<PreventorButton>(R.id.identityVerificationButton)

        val config = preventorSDK.getConfig()

        config.setFlowType("YOUR_FLOW_TYPE");
        config.getCredentials().setApiKey("YOUR_API_KEY");
        config.getCredentials().setClientSecret("YOUR_CLIENT_SECRET");
        config.getCredentials().setTenant("YOUR_TENANT");
        config.getCredentials().setBanknu("YOUR_BANKNU");
        config.getCredentials().setEnv("YOUR_ENV");

        config.getCurrentUserInfo().setCifCode("YOUR_CIFCODE");


        config.getTheme().setLogo(R.drawable.your_logo);  //YOUR_LOGO
        config.getTheme().getButton().setBackgroundColor("YOUR_BACKGROUND_COLOR");
        config.getTheme().getButton().getFont().setFontFamily("YOUR_FONT_FAMILY");
        config.getTheme().getButton().getFont().setFontSize(14); //"YOUR_FONT_SIZE"
        config.getTheme().getButton().getFont().setFontColor("YOUR_FONT_COLOR");

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
