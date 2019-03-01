package com.sritechsoftsolutions.sensortest

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var vib:Vibrator= getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        var sManager:SensorManager= getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var sensor:Sensor=sManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sManager.registerListener(object :SensorEventListener{
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }

            override fun onSensorChanged(event: SensorEvent?) {
                var values=event?.values
                textView.setText("X Value: ${values!![0]}")
                textView2.setText("Y Value: ${values!![1]}")

                if (values[0]>10||values[1]>10)
                {
                    vib.vibrate(5000L)
                }
            }
        },sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
}
