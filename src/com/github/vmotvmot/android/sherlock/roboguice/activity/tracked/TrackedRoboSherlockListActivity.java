package com.github.vmotvmot.android.sherlock.roboguice.activity.tracked;

import android.os.Bundle;

import com.github.vmotvmot.android.sherlock.roboguice.activity.RoboSherlockListActivity;
import com.google.android.apps.analytics.easytracking.EasyTracker;

public class TrackedRoboSherlockListActivity extends RoboSherlockListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Only one call to setContext is needed, but additional calls don't hurt
		// anything, so we'll always make the call to ensure EasyTracker gets
		// setup properly.
		EasyTracker.getTracker().setContext(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		// This call will ensure that the Activity in question is tracked properly,
		// based on the setting of ga_auto_activity_tracking parameter.  It will
		// also ensure that startNewSession is called appropriately.
		EasyTracker.getTracker().trackActivityStart(this);
	}

	@Override
	@SuppressWarnings("deprecation")
	public Object onRetainNonConfigurationInstance() {
		Object o = super.onRetainNonConfigurationInstance();

		// This call is needed to ensure that configuration changes (like
		// orientation) don't result in new sessions.  Remove this line if you want
		// configuration changes to for a new session in Google Analytics.
		EasyTracker.getTracker().trackActivityRetainNonConfigurationInstance();
		return o;
	}

	@Override
	protected void onStop() {
		super.onStop();

		// This call is needed to ensure time spent in an Activity and an
		// Application are measured accurately.
		EasyTracker.getTracker().trackActivityStop(this);
	}

}
