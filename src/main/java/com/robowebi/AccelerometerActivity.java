/**
 * Copyright 2014 Jeff McKenzie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.robowebi;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.TextView;

/**
 * @author jeff
 * 
 */
public class AccelerometerActivity extends Activity implements
		SensorEventListener {

	private static final String tag = "AccelerometerActivity";
	private SensorManager sensorManager;

	private TextView vendor;
	private TextView version;
	private TextView accuracy;
	private TextView timestamp;
	private TextView sensorName;
	private TextView sensorPower;
	private TextView sensorResolution;
	private TextView sensorType;
	private TextView sensorMinDelay;
	private TextView sensorX;
	private TextView sensorY;
	private TextView sensorZ;

	private Vibrator vibrator;
	private PowerManager powerManager;
	private Sensor accelerometerSensor;
	private Sensor lightSensor;
	private Sensor magneticFieldUncalibratedSensor;
	private Sensor orientationSensor;
	private Sensor proximitySensor;
	private Sensor gravitySensor;
	private Sensor linearAccelerationSensor;
	private Sensor rotationVectorSensor;
	private WakeLock wakeLock;

	@Override
	protected void onStart() {
		super.onStart();
		sensorManager.registerListener(this, accelerometerSensor,
				SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, lightSensor,
		// SensorManager.SENSOR_DELAY_UI);
		sensorManager.registerListener(this, orientationSensor,
				SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, proximitySensor,
		// SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, gravitySensor,
		// SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, linearAccelerationSensor,
		// SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, rotationVectorSensor,
		// SensorManager.SENSOR_DELAY_UI);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSensorChanged(SensorEvent event) {

		switch (event.sensor.getType()) {
		// noisy
		case Sensor.TYPE_ACCELEROMETER:
			Log.d(tag, String.format(
					"TYPE_ACCELEROMETER: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
			Log.v(tag, "accuracy: " + String.valueOf(event.accuracy));
			Log.v(tag, "timestamp: " + String.valueOf(event.timestamp));
			Log.v(tag, "sensorName: " + String.valueOf(event.sensor.getName()));
			Log.v(tag,
					"sensorPower: " + String.valueOf(event.sensor.getPower()));
			Log.v(tag,
					"sensorResolution: "
							+ String.valueOf(event.sensor.getResolution()));
			Log.v(tag, "sensorType: " + String.valueOf(event.sensor.getType()));
			Log.v(tag,
					"sensorMinDelay: "
							+ String.valueOf(event.sensor.getMinDelay()));
			Log.d(tag, String.format("x: %f, y: %f, z: %f", event.values[0],
					event.values[1], event.values[2]));
			try {
				vendor.setText(event.sensor.getVendor());
				version.setText(String.valueOf(event.sensor.getVersion()));
				accuracy.setText(String.valueOf(event.accuracy));
				timestamp.setText(String.valueOf(event.timestamp));
				sensorName.setText(String.valueOf(event.sensor.getName()));
				sensorPower.setText(String.valueOf(event.sensor.getPower()));
				sensorResolution.setText(String.valueOf(event.sensor
						.getResolution()));
				sensorType.setText(String.valueOf(event.sensor.getType()));
				sensorMinDelay.setText(String.valueOf(event.sensor
						.getMinDelay()));
				sensorX.setText(String.valueOf(event.values[0]));
				sensorY.setText(String.valueOf(event.values[1]));
				sensorZ.setText(String.valueOf(event.values[2]));
			} catch (Exception e) {
				Log.d(tag, "Exception updating textFields", e);
			}
			break;

		case Sensor.TYPE_ORIENTATION:
			Log.d(tag, String.format(
					"TYPE_ORIENTATION: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
			Log.d(tag, String.format("Azimuth: %f Pitch: %f Roll: %f",
					event.values[0], event.values[1], event.values[2]));
			break;

		case Sensor.TYPE_PROXIMITY:
			Log.d(tag, String.format(
					"TYPE_PROXIMITY: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
			Log.d(tag, "Value: " + String.valueOf(event.values[0]));
			break;

		case Sensor.TYPE_LIGHT:
			Log.d(tag, String.format("TYPE_LIGHT: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
			Log.d(tag, "Value: " + String.valueOf(event.values[0]));
			break;
		// noisy
		case Sensor.TYPE_GRAVITY:
			Log.d(tag, String.format("TYPE_GRAVITY: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
			Log.d(tag, String.format("x: %f, y: %f, z: %f", event.values[0],
					event.values[1], event.values[2]));
			break;
		// noisy
		case Sensor.TYPE_LINEAR_ACCELERATION:
			Log.d(tag, String.format(
					"TYPE_LINEAR_ACCELERATION: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
			Log.d(tag, String.format("x: %f, y: %f, z: %f", event.values[0],
					event.values[1], event.values[2]));
			break;
		// noisy
		case Sensor.TYPE_ROTATION_VECTOR:
			Log.d(tag, String.format(
					"TYPE_ROTATION_VECTOR: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
			Log.d(tag, String.format("x: %f, y: %f, z: %f", event.values[0],
					event.values[1], event.values[2]));
			break;

		default:
			Log.d(tag, String.format(
					"Unknown SensorType: vendor = %s version = %d",
					event.sensor.getVendor(), event.sensor.getVersion()));
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		Log.d(tag, String.format(
				"Sensor Accuracy Changed: %s Vendor: %s accuracy: %d",
				sensor.getName(), sensor.getVendor(), accuracy));
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(tag, "onCreate");
		setContentView(R.layout.sensor_layout);

		// TODO Refactor this block into the main activity to dynamically
		// create the buttons based on the detected sensors
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
		for (Sensor i : sensorList) {
			Log.d(tag, "Sensor: " + i.getName());
			Log.d(tag, "Type: " + i.getType());

		}
		accelerometerSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		// magneticFieldUncalibratedSensor = sensorManager
		// .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
		orientationSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		// proximitySensor =
		// sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		// lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		// gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
		// linearAccelerationSensor = sensorManager
		// .getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		// rotationVectorSensor = sensorManager
		// .getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		/* end sensor stuff */

		/* power manager stuff */
		powerManager = (PowerManager) getSystemService(POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(
				PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass().getName());
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(tag, "onResume");

		vendor = (TextView) findViewById(R.id.vendor);
		version = (TextView) findViewById(R.id.version);
		accuracy = (TextView) findViewById(R.id.accuracy);
		timestamp = (TextView) findViewById(R.id.timestamp);
		sensorName = (TextView) findViewById(R.id.sensorName);
		sensorPower = (TextView) findViewById(R.id.sensorPower);
		sensorResolution = (TextView) findViewById(R.id.sensorResolution);
		sensorType = (TextView) findViewById(R.id.sensorType);
		sensorMinDelay = (TextView) findViewById(R.id.sensorMinDelay);
		sensorX = (TextView) findViewById(R.id.sensorX);
		sensorY = (TextView) findViewById(R.id.sensorY);
		sensorZ = (TextView) findViewById(R.id.sensorZ);

		sensorManager.registerListener(this, accelerometerSensor,
				SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, lightSensor,
		// SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, magneticFieldUncalibratedSensor,
		// SensorManager.SENSOR_DELAY_UI);
		sensorManager.registerListener(this, orientationSensor,
				SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, proximitySensor,
		// SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, gravitySensor,
		// SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, linearAccelerationSensor,
		// SensorManager.SENSOR_DELAY_UI);
		// sensorManager.registerListener(this, rotationVectorSensor,
		// SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(tag, "onPause");
		sensorManager.unregisterListener(this);
	}
}
