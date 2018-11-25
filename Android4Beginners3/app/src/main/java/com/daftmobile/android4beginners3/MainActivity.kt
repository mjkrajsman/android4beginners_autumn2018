package com.daftmobile.android4beginners3

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : LifecycleLoggingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showColorButton.setOnClickListener(this::showColorActivity)
        inputButton.setOnClickListener(this::showInputActivity)
    }

    private fun showColorActivity(view: View) {
        // tutaj otwórz ColorActivity
        val intent = Intent(this, ColorActivity::class.java)
        intent.putExtra("colorKey", RandomColor.generate())
        startActivity(intent)
    }

    private fun showInputActivity(view: View) {
        // tutaj otwórz InputActivity
        val intent = Intent(this, InputActivity::class.java)
        startActivityForResult(intent, 101)
    }

    private object RandomColor {
        private val random = Random()

        fun generate(): Int = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if(requestCode==101){
            if(resultCode == Activity.RESULT_OK){
             //   inputData.text = data!!.getStringExtra("result"), Toast (...)
            }
        }
    }
}
