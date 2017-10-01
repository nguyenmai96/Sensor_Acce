package com.example.maithihuong.sensor_proximity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

    public class MainActivity extends Activity implements SensorEventListener {
        private SensorManager sensorManager;
        private   Sensor proximitySensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
////        if (proximitySensor==null){
////            Log.e("TAG","Sensor Proximity is not available");
////        }


    }



        @Override
        public void onSensorChanged(SensorEvent event) {
            float distance=event.values[0];
            if (distance<proximitySensor.getMaximumRange()){
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }else {
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        protected void onResume() {
            super.onResume();
            sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected void onPause() {
            super.onPause();
            sensorManager.unregisterListener(this);
        }
    }
