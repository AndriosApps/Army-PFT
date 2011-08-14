package com.andrios.apft;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class APFTActivity extends Activity{
	
	private static int MAX_PUSHUPS = 80;//max pushups is 77
	private static int MIN_PUSHUPS = 0;
	private static int MAX_SITUPS = 100;//max pushups is 77
	private static int MIN_SITUPS = 0;
	private static int MAX_RUN = 1590;// 26:30 minutes
	private static int MIN_RUN = 600;// 10 minutes
	
	
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
	private String array_spinner[];
	boolean pushupchanged = false, situpchanged = false, runchanged = false;
	
	int age = 17, pushups = 0, situps = 0, runtime = 600, minutes, seconds;
	int pushupScore = -1;
	int situpScore = -1;
	int runScore = -1;
	boolean male;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.apftactivity);
	        
	        
	        getExtras();
	        setConnections();
	        setOnClickListeners();
	        
	    
	    }
	private void getExtras() {
		Intent intent = this.getIntent();
		mData = (AndriosData) intent.getSerializableExtra("data");
			
	}


	private void setConnections() {
		array_spinner=new String[8];
		array_spinner[0]="17-21";
		array_spinner[1]="22-26";
		array_spinner[2]="27-31";
		array_spinner[3]="32-36";
		array_spinner[4]="37-41";
		array_spinner[5]="42-46";
		array_spinner[6]="47-51";
		array_spinner[7]="52-56";
		ageSpinner = (Spinner) findViewById(R.id.APFTActivityAgeSpinner);
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, array_spinner);
		ageSpinner.setAdapter(adapter);
		
		maleRDO  = (RadioButton) findViewById(R.id.APFTActivityMaleRDO); 
		pushupSeekBar = (SeekBar) findViewById(R.id.APFTActivityPushupSeekBar); 
		situpSeekBar = (SeekBar) findViewById(R.id.APFTActivitySitUpSeekBar);
		runSeekBar = (SeekBar) findViewById(R.id.APFTActivityRunTimeSeekBar);
		 
		scoreLBL = (TextView) findViewById(R.id.APFTActivityScoreLBL);
		pushupLBL = (TextView) findViewById(R.id.APFTActivityPushUpLBL); 
		situpLBL = (TextView) findViewById(R.id.APFTActivitySitUpLBL);
		runLBL = (TextView) findViewById(R.id.APFTActivityRunLBL);
		
		pushupScoreLBL = (TextView) findViewById(R.id.APFTActivityPushupScoreLBL);
		situpScoreLBL = (TextView) findViewById(R.id.APFTActivitySitupScoreLBL);
		runScoreLBL = (TextView) findViewById(R.id.APFTActivityRunScoreLBL);
		
		

		pushupUpBTN = (Button) findViewById(R.id.APFTActivityPushupsUpBTN);
		situpUpBTN = (Button) findViewById(R.id.APFTActivitySitupsUpBTN);
		runUpBTN = (Button) findViewById(R.id.APFTActivitySecondsUpBTN);

		pushupDownBTN = (Button) findViewById(R.id.APFTActivityPushupsDownBTN);
		situpDownBTN = (Button) findViewById(R.id.APFTActivitySitupsDownBTN);
		runDownBTN = (Button) findViewById(R.id.APFTActivitySecondsDownBTN);
		
		
		
		pushupSeekBar.setMax(MAX_PUSHUPS - MIN_PUSHUPS);
		situpSeekBar.setMax(MAX_SITUPS - MIN_SITUPS);
		runSeekBar.setMax(MAX_RUN - MIN_RUN);
		runLBL.setText(formatTimer());
		
	}

	private void setOnClickListeners() {
		
		maleRDO.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				calculateScore();
				
			}
			
		});
	 ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			int posit = ageSpinner.getSelectedItemPosition();
			if(posit == 0){
				age = 17;
			}else if(posit == 1){
				age = 22;
			}else if(posit == 2){
				age = 27;
			}else if(posit == 3){
				age = 32;
			}else if(posit == 4){
				age = 37;
			}else if(posit == 5){
				age = 42;
			}else if(posit == 6){
				age = 47;
			}else if(posit == 7){
				age = 52;
			}
			calculateScore();
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		 
	 });
	 
	 /*
	  * PUSHUPS
	  */
	 
	 pushupSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			pushups = arg1 + MIN_PUSHUPS;
			pushupLBL.setText(Integer.toString(pushups));
			pushupchanged = true;
			calculateScore();
			
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		 
	 });
	 
	 pushupUpBTN.setOnClickListener(new OnClickListener(){

		public void onClick(View v) {
			if(pushups < MAX_PUSHUPS){
				pushups += 1;
			}
			pushupSeekBar.setProgress(pushups - MIN_PUSHUPS);
			
		}
		 
	 });
	 
	 pushupDownBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				if(pushups > MIN_PUSHUPS){
					pushups -= 1;
				}
				pushupSeekBar.setProgress(pushups - MIN_PUSHUPS);
				
			}
			 
		 });
	 
	 
	 /*
	  * Situps
	  */
	 
	 situpSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				situps = arg1 + MIN_SITUPS;
				situpLBL.setText(Integer.toString(situps));
				situpchanged = true;
				calculateScore();
				
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
	 
	 /*
	  * 2 Mile Run
	  */
	 
	 runSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				runtime = arg1 + MIN_RUN;
				
				runLBL.setText(formatTimer());
				runchanged = true;
				calculateScore();
				
				
				
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
	 
	 runUpBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				if(runtime < MAX_RUN){
					runtime += 1;
				}
				runSeekBar.setProgress(runtime - MIN_RUN);
				
			}
			 
		 });
		 
		 runDownBTN.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					if(runtime > MIN_RUN){
						runtime -= 1;
					}
					runSeekBar.setProgress(runtime - MIN_RUN);
					
				}
				 
			 });
		
	}
	
	private String formatTimer(){
		String minutesTXT, secondsTXT;
		minutes = (Integer) runtime / 60;
		seconds = runtime % 60;
		if(minutes < 10){
			minutesTXT = "0"+Integer.toString(minutes);
		}else{
			minutesTXT = Integer.toString(minutes);
		}
		if(seconds < 10){
			secondsTXT = "0"+Integer.toString(seconds);
		}else{
			secondsTXT = Integer.toString(seconds);
		}
		
		String formattedTime = minutesTXT + ":" + secondsTXT;
		return formattedTime;
		
		
	}
	
	private void calculateScore(){
		if(pushupchanged){
			scorePushups();
		}
		if(situpchanged){
			scoreSitups();
		}
		if(runchanged){
			scoreRun();
		}
		
		if(runchanged && situpchanged && pushupchanged){
			int totalScore = (runScore + situpScore + pushupScore)/3;
			if(runScore < 60 || pushupScore < 60 || pushupScore < 60){
				scoreLBL.setText("Events Failed: " + totalScore);
			}else{
				scoreLBL.setText("Score: " + totalScore);
			}
			
		}
		
	}
	
	private void scorePushups(){
		if(maleRDO.isChecked()){
			if(age == 17){
				if(pushups >= mData.pushupMale17.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale17[pushups];
				}	
			}else if(age == 22){
				if(pushups >= mData.pushupMale22.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale22[pushups];
				}	
			}else if(age == 27){
				if(pushups >= mData.pushupMale27.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale27[pushups];
				}	
			}else if(age == 32){
				if(pushups >= mData.pushupMale32.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale32[pushups];
				}	
			}else if(age == 37){
				if(pushups >= mData.pushupMale37.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale37[pushups];
				}	
			}else if(age == 42){
				if(pushups >= mData.pushupMale42.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale42[pushups];
				}	
			}else if(age == 47){
				if(pushups >= mData.pushupMale47.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale47[pushups];
				}	
			}else if(age == 52){
				if(pushups >= mData.pushupMale52.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupMale52[pushups];
				}	
			}
		}else{
			if(age == 17){
				if(pushups >= mData.pushupFemale17.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale17[pushups];
				}	
			}else if(age == 22){
				if(pushups >= mData.pushupFemale22.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale22[pushups];
				}	
			}else if(age == 27){
				if(pushups >= mData.pushupFemale27.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale27[pushups];
				}	
			}else if(age == 32){
				if(pushups >= mData.pushupFemale32.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale32[pushups];
				}	
			}else if(age == 37){
				if(pushups >= mData.pushupFemale37.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale37[pushups];
				}	
			}else if(age == 42){
				if(pushups >= mData.pushupFemale42.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale42[pushups];
				}	
			}else if(age == 47){
				if(pushups >= mData.pushupFemale47.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale47[pushups];
				}	
			}else if(age == 52){
				if(pushups >= mData.pushupFemale52.length){
					pushupScore = 100;
				}else{
					pushupScore = mData.pushupFemale52[pushups];
				}	
			}
		}
			if(pushupScore >= 60){
				pushupScoreLBL.setText(Integer.toString(pushupScore));
			}else{
				pushupScoreLBL.setText("Fail");
			}
		
		
		
	}
	
	private void scoreSitups(){
		
			if(situps < 21){
				situpScore = 0;
			}else{
				int i = situps - 21;
				if(age == 17){
					if(i >= mData.situp17.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp17[i];
					}	
				}else if(age == 22){
					if(i >= mData.situp22.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp22[i];
					}	
				}else if(age == 27){
					if(i >= mData.situp27.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp27[i];
					}	
				}else if(age == 32){
					if(i >= mData.situp32.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp32[i];
					}	
				}else if(age == 37){
					if(i >= mData.situp37.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp37[i];
					}	
				}else if(age == 42){
					if(i >= mData.situp42.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp42[i];
					}	
				}else if(age == 47){
					if(i >= mData.situp47.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp47[i];
					}	
				}else if(age == 52){
					if(i >= mData.situp52.length){
						situpScore = 100;
					}else{
						situpScore = mData.situp52[i];
					}	
				}
			}
			
		
			if(situpScore >= 60){
				situpScoreLBL.setText(Integer.toString(situpScore));
			}else{
				situpScoreLBL.setText("Fail");
			}
			
		
		
		
	}
	private int findRunIndex(){
		int index = -1;
		int time = MAX_RUN;
		while(runtime <= time){
			
			time -= 6;
			index += 1;
		}
		return index;
	}
	
	private void scoreRun(){
		int index = findRunIndex();
		if(maleRDO.isChecked()){
			if(age == 17){
				if(index >= mData.runMale17.length){
					runScore = 100;
				}else{
					runScore = mData.runMale17[index];
					
				}
			}else if(age == 22){
				if(index >= mData.runMale22.length){
					runScore = 100;
				}else{
					runScore = ((mData.runMale22[index]));
				}
			}else if(age == 27){
				if(index >= mData.runMale27.length){
					runScore = 100;
				}else{
					runScore = ((mData.runMale27[index]));
				}
			}else if(age == 32){
				if(index >= mData.runMale32.length){
					runScore = 100;
				}else{
					runScore = ((mData.runMale32[index]));
				}
			}else if(age == 37){
				if(index >= mData.runMale37.length){
					runScore = 100;
				}else{
					runScore = ((mData.runMale37[index]));
				}
			}else if(age == 42){
				if(index >= mData.runMale42.length){
					runScore = 100;
				}else{
					runScore = ((mData.runMale42[index]));
				}
			}else if(age == 47){
				if(index >= mData.runMale47.length){
					runScore = 100;
				}else{
					runScore = ((mData.runMale47[index]));
				}
			}else if(age == 52){
				if(index >= mData.runMale52.length){
					runScore = 100;
				}else{
					runScore = ((mData.runMale52[index]));
				}
			}
		}else{
			if(age == 17){
				if(index >= mData.runFemale17.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale17[index]));
				}
			}else if(age == 22){
				if(index >= mData.runFemale22.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale22[index]));
				}
			}else if(age == 27){
				if(index >= mData.runFemale27.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale27[index]));
				}
			}else if(age == 32){
				if(index >= mData.runFemale32.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale32[index]));
				}
			}else if(age == 37){
				if(index >= mData.runFemale37.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale37[index]));
				}
			}else if(age == 42){
				if(index >= mData.runFemale42.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale42[index]));
				}
			}else if(age == 47){
				if(index >= mData.runFemale47.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale47[index]));
				}
			}else if(age == 52){
				if(index >= mData.runFemale52.length){
					runScore = 100;
				}else{
					runScore = ((mData.runFemale52[index]));
				}
			}
		}
		
		if(runScore >= 60){
			runScoreLBL.setText(Integer.toString(runScore));
		}else{
			runScoreLBL.setText("Fail");
		}
		
		
		
	}
}
