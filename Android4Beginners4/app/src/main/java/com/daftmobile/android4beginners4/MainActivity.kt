package com.daftmobile.android4beginners4

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_UI
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val liveData = LuminosityLiveData(sensorManager)
        liveData.observe(this, Observer(this::updateView)) //updateView dostaje luminosity, bo takiego typu jest liveData
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

//    override fun onStart() {
//        super.onStart()
//        sensorManager.registerListener(listener, sensor, SENSOR_DELAY_UI)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        sensorManager.unregisterListener(listener) //bardzo ważne! zwalnia pamięć.
//    }

    private fun updateView(luminosity: Luminosity?) {
        if (luminosity == null) return
        luxView.text = luminosity.lux.toString()
        val background = Color.HSVToColor(floatArrayOf(50f, 0.5f, luminosity.fractionOfIndoorLight))
        colorView.setBackgroundColor(background)
    }

//    private val listener = object : SensorEventListener {
//        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
//
//        override fun onSensorChanged(event: SensorEvent) {
//
//            val luminosity = Luminosity(event.values[0])
//            updateView(luminosity)
//        }
//    }
}
