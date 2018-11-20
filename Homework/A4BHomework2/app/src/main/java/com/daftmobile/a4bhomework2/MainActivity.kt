package com.daftmobile.a4bhomework2

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        red.setOnClickListener { handleRedButtonClick(it) }
        green.setOnClickListener { handleGreenButtonClick(it) }
        blue.setOnClickListener { handleBlueButtonClick(it) }
    }

    private fun handleRedButtonClick(view: View) {
        textView.text = getString(R.string.red)
        textView.setTextColor(Color.RED)
    }

    private fun handleGreenButtonClick(view: View) {
        textView.text = getString(R.string.green)
        textView.setTextColor(Color.GREEN)
    }

    private fun handleBlueButtonClick(view: View) {
        textView.text = getString(R.string.blue)
        textView.setTextColor(Color.BLUE)
    }


}
