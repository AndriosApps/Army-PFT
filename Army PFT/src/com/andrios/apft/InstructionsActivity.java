package com.andrios.apft;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class InstructionsActivity extends Activity {

	private static String TC32220 = "TC_3-22_20_PRT_3_.pdf";
	private static String TC32220URL = "http://www.usarak.army.mil/ncoa/doc/TC%203-22%2020%20%28PRT%29%20%283%29.pdf";
	private static String FM2021 = "FM_21-20.pdf";
	private static String FM2021URL = "http://bodyweightexercisetips.com/APFT/wp-content/uploads/PDF8675309/FM%2021-20.pdf";
	private static String AR6009 = "r600_9.pdf";
	private static String AR6009URL = "http://www.apd.army.mil/pdffiles/r600_9.pdf";
	
	AdView adView;
	AdRequest request;
	GoogleAnalyticsTracker tracker;
	Button rateBTN, tc3_22_20BTN, fm2021BTN, ar6009BTN;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.instructionsactivity);
        

        setConnections();
        setOnClickListeners();
       setTracker();
    }
    
	private void setTracker() {
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.start(this.getString(R.string.ga_api_key),
				getApplicationContext());
	}

	@Override
	public void onResume() {
		super.onResume();
		tracker.trackPageView("/" + this.getLocalClassName());
	}

	@Override
	public void onPause() {
		super.onPause();
		tracker.dispatch();
	}
    

	private void setConnections() {
		rateBTN = (Button) findViewById(R.id.instructionActivityRateBTN);
		fm2021BTN = (Button) findViewById(R.id.fm21_20BTN);
		tc3_22_20BTN = (Button) findViewById(R.id.tc32220_20BTN);
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
		
		fm2021BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "FM 20-21", // Label
				            0);       // Value
				 try{
					 open(FM2021);
				 }catch(Exception e){
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(FM2021URL));
					startActivity(browserIntent);
				 }
			}
			
		});	
		
		tc3_22_20BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "TC 3-22.20", // Label
				            0);       // Value
				
				 try{
					 open(TC32220);
				 }catch(Exception e){
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(TC32220URL));
					startActivity(browserIntent);
				 }
				 
				 
				 
				
			}
			
		});
			
			
		ar6009BTN.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				 tracker.trackEvent(
				            "Clicks",  // Category
				            "Link",  // Action
				            "AR 600-9", // Label
				            0);       // Value
				 try{
					 open(AR6009);
				 }catch(Exception e){
					 Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(AR6009URL));
					startActivity(browserIntent);
				 }
				
			}
			
		});
		

		
	}
	
	private void open(String filename){
		
		String PATH = Environment.getExternalStorageDirectory()
                + "/download/";
		
		File file = new File(PATH + filename);
		if (file.exists()) {
			 System.out.println("file exists");
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");

            try {
            	System.out.println("Start Activity");
                startActivity(intent);
            } 
            catch (ActivityNotFoundException e) {
                Toast.makeText(InstructionsActivity.this, 
                    "No Application Available to View PDF", 
                    Toast.LENGTH_SHORT).show();
            }
		}else{
			float f = 1/0;
		}
	}
	
	public void download(String DownloadUrl, String fileName) {

		   try {
		           File root = android.os.Environment.getExternalStorageDirectory();               

		           File dir = new File (root.getAbsolutePath() + "/download");
		           if(dir.exists()==false) {
		                dir.mkdirs();
		           }

		           URL url = new URL(DownloadUrl); //you can write here any link
		           File file = new File(dir, fileName);

		           long startTime = System.currentTimeMillis();
		           Log.d("DownloadManager", "download begining");
		           Log.d("DownloadManager", "download url:" + url);
		           Log.d("DownloadManager", "downloaded file name:" + fileName);

		           /* Open a connection to that URL. */
		           URLConnection ucon = url.openConnection();

		           /*
		            * Define InputStreams to read from the URLConnection.
		            */
		           InputStream is = ucon.getInputStream();
		           BufferedInputStream bis = new BufferedInputStream(is);

		           /*
		            * Read bytes to the Buffer until there is nothing more to read(-1).
		            */
		           ByteArrayBuffer baf = new ByteArrayBuffer(5000);
		           int current = 0;
		           while ((current = bis.read()) != -1) {
		              baf.append((byte) current);
		           }


		           /* Convert the Bytes read to a String. */
		           FileOutputStream fos = new FileOutputStream(file);
		           fos.write(baf.toByteArray());
		           fos.flush();
		           fos.close();
		           Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

		   } catch (IOException e) {
		       Log.d("DownloadManager", "Error: " + e);
		   }

		}
	

}

