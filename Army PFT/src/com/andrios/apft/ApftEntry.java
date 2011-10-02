package com.andrios.apft;

import java.util.Calendar;

public class ApftEntry extends LogEntry{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1538116430747812374L;
	boolean isPushupsWaived;
	boolean isSitupsWaived;
	boolean isCardioWaived;
	boolean isWaived0;
	boolean isWaived1;
	boolean isWaived2;
	boolean isWaived3;
	boolean isWaived4;
	boolean isWaived5;
	boolean isWaived6;
	String alternateCardio;
	String pushups;
	String situps;
	String run;
	String pushupScore;
	String situpScore;
	String runScore;
	String totalScore;
	
	
	
	public ApftEntry(String pushups, String situps, String run, String pushupScore,
		String situpScore, String runScore, String totalScore){
		this.name = "APFT";
		this.c = Calendar.getInstance();
		this.pushups = pushups;
		this.situps = situps;
		this.run = run;
		this.pushupScore = pushupScore;
		this.situpScore = situpScore;
		this.runScore = runScore;
		this.totalScore = totalScore;
		this.layout = R.layout.list_item_apft_entry;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.andrios.prt.LogEntry#getScoreString()
	 */
	@Override
	public String getScoreString() {
		
		return getTotalScore();
	}
	
	/*
	 * Getter Methods
	 */
	
	public String getSitups(){
		return situps;
	}
	
	public String getPushups(){
		return pushups;
	}
	
	public String getRun(){
		return run;
	}
	
	public String getPushupScore(){
		return pushupScore;
	}
	
	public String getSitupScore(){
		return situpScore;
	}
	
	public String getRunScore(){
		return runScore;
	}
	
	public String getTotalScore(){
		return totalScore;
	}
	
	public String getAlternateCardio(){
		return alternateCardio;
	}
	
	/*
	 * Setter Methods
	 */
	
	public void setAlternateCardio(String alternateCardio){
		this.alternateCardio = alternateCardio;
	}

}
