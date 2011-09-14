package com.andrios.apft;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class InstructionsActivity extends Activity {

	
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	Button rateBTN, fm21_20BTN, ar6009BTN;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructionsactivity);
        

        setConnections();
        setOnClickListeners();
       setTracker();
    }
    

	private void setConnections() {
		rateBTN = (Button) findViewById(R.id.instructionActivityRateBTN);
		fm21_20BTN = (Button) findViewById(R.id.fm21_20BTN);
		ar6009BTN = (Button) findViewById(R.id.ar6009BTN);
		
		
		adView = (AdView)this.findViewById(R.id.instructionsAdView);
	      
	    request = new AdRequest();
		request.setTesting(false);
		adView.loadAd(request);
		
	}

	private void setOnClickListeners() {
		rateBTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://details?id=com.andrios.apft"));
				startActivity(intent);

				
			}
			
		});
		
		fm21_20BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "6100.13", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.apft-standards.com/files/fm21_20.pdf"));
				startActivity(browserIntent);
			}
			
		});
		
		ar6009BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "AR 600-9", // Label
				            0);       // Value
				Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.apd.army.mil/pdffiles/r600_9.pdf"));
				startActivity(browserIntent);
			}
			
		});
		

		
	}
	
	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();

	    // Start the tracker in manual dispatch mode...
	    tracker.start("UA-23366060-6", this);
	    
		
	}
	
	public void onResume(){
		super.onResume();
		tracker.trackPageView("BCA");
	}
	
	public void onPause(){
		super.onPause();
		tracker.dispatch();
	}
}

