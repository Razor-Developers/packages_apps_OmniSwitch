/*
 *  Copyright (C) 2013 The OmniROM Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.omnirom.omniswitch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private static final String TAG = "RecentsMainActivity";

	private ActivityReceiver mReceiver;

	public class ActivityReceiver extends BroadcastReceiver {
		public static final String ACTION_FINISH = "org.omnirom.omniswitch.ACTION_FINISH_ACTIVITY";

		@Override
		public void onReceive(final Context context, Intent intent) {
			String action = intent.getAction();
			Log.d(TAG, "onReceive " + action);
			if (ACTION_FINISH.equals(action)) {
				finish();
			}
		}
	}

	@Override
	public void onStart() {
		Log.d(TAG, "onStart");
		super.onStart();
	}

	@Override
	public void onStop() {
		Log.d(TAG, "onStop");
		super.onStop();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");

		mReceiver = new ActivityReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ActivityReceiver.ACTION_FINISH);

		registerReceiver(mReceiver, filter);

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onPause() {
		Log.d(TAG, "onPause");
		Intent hideRecent = new Intent(
				RecentsService.RecentsReceiver.ACTION_HIDE_RECENTS);
		sendBroadcast(hideRecent);
		super.onPause();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "onResume");
		Intent hideRecent = new Intent(
				RecentsService.RecentsReceiver.ACTION_SHOW_RECENTS2);
		sendBroadcast(hideRecent);
		super.onResume();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
}