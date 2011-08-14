package com.andrios.apft;

import android.app.Activity;
import android.os.Bundle;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    
	private AndriosData mData; //Read in from saved file, passed to all future intents.
	
	
	/** Called when the activity is first created. */
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        
        readData();
        setConnections();
    }

	private void readData() {
		mData = new AndriosData();
		
	}

	private void setConnections() {
		TabHost mTabHost = getTabHost();
        Intent intent;
        Resources res = getResources(); 
        
        //Setup for Home Tab (Tab 0)
        intent = new Intent().setClass(this, APFTActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("APFT").setIndicator("",res.getDrawable(R.drawable.icon))
        		.setContent(intent));
        
        //Setup for BCA Tab (Tab 1)
        intent = new Intent().setClass(this, BCAActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("BCA").setIndicator("",res.getDrawable(R.drawable.icon))
        		.setContent(intent));
        
        //Setup for Profile Tab (Tab 2)
        intent = new Intent().setClass(this, APFTActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("BCA").setIndicator("",res.getDrawable(R.drawable.icon))
        		.setContent(intent));
             
        //Setup for Exercise Tab (Tab 3)
        intent = new Intent().setClass(this, APFTActivity.class);
        intent.putExtra("data", mData);
        mTabHost.addTab(mTabHost.newTabSpec("Instructions").setIndicator("",res.getDrawable(R.drawable.icon))
        		.setContent(intent));
       

        //Set Tab host to Home Tab
        mTabHost.setCurrentTab(0);
	}
}