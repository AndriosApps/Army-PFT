package com.andrios.apft;


import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class APFTActivity extends Activity{

	RadioButton maleRDO, femaleRDO;
	Spinner ageSpinner;
	SeekBar pushupSeekBar, situpSeekBar, runSeekBar;
	TextView pushupLBL, situpLBL,runLBL, scoreLBL;
	TextView pushupScoreLBL, situpScoreLBL, runScoreLBL;
	Button runUpBTN, runDownBTN;
	Button pushupUpBTN, pushupDownBTN, situpUpBTN, situpDownBTN;
	AndriosData mData;
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	boolean pushupchanged = false, situpchanged = false, runchanged = false;
	
	int age = 18, pushups = 0, situps = 0, runtime = 0, minutes, seconds;
	boolean male;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.apftactivity);
	        
	        /*
	        getExtras();
	        setConnections();
	        setOnClickListeners();
	        setTracker();
	    */
	    }
	
}
