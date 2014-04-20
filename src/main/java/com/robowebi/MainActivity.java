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
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * @author jeff
 * 
 */
public class MainActivity extends Activity {

	private static final String tag = "MainActivity";
	private SensorManager sensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(tag, "onCreate");
		setContentView(R.layout.main_layout);

		// TODO Refactor this block into the main activity to dynamically
		// create the buttons based on the detected sensors
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
		for (Sensor i : sensorList) {
			Log.d(tag, "Sensor: " + i.getName() + " Type: " + i.getType());
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public void onAccelerometer(View v) {
		Log.v(tag, "onAccelerometer");
		startActivity(new Intent(this, AccelerometerActivity.class));
	}

	public void onMagneticField(View v) {
		Log.v(tag, "onAccelerometer");
		startActivity(new Intent(this, MagneticFieldActivity.class));
	}

	public void onOrientation(View v) {
		Log.v(tag, "onAccelerometer");
		startActivity(new Intent(this, OrientationActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(tag, "onCreateOptionsMenu");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d(tag, "onOptionsItemSelected");
		switch (item.getItemId()) {
		case R.id.about:
			startActivity(new Intent(this, AboutActivity.class));
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
