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

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author jeff
 * 
 */
public class OrientationActivity extends Activity implements
		SensorEventListener {

	private static final String tag = "OrientationActivity";
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

	private Sensor orientationSensor;

	@Override
	protected void onStart() {
		super.onStart();
		sensorManager.registerListener(this, orientationSensor,
				SensorManager.SENSOR_DELAY_UI);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			Log.d(tag, String.format("TYPE_ORIENTATION|%s|%d|%f|%f|%f",
					event.sensor.getVendor(), event.sensor.getVersion(),
					event.values[0], event.values[1], event.values[2]));
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

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		orientationSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ORIENTATION);
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

		sensorManager.registerListener(this, orientationSensor,
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(tag, "onPause");
		sensorManager.unregisterListener(this);
	}
}