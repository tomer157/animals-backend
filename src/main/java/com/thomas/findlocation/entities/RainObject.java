package com.thomas.findlocation.entities;

public class RainObject {
	double isRainLat = 0;
	double isRainLng = 0;
	
	public RainObject(){}

	public double getIsRainLat() {
		return isRainLat;
	}
	
	

	public RainObject(double isRainLat, double isRainLng) {
		super();
		this.isRainLat = isRainLat;
		this.isRainLng = isRainLng;
	}

	public void setIsRainLat(double isRainLat) {
		this.isRainLat = isRainLat;
	}

	public double getIsRainLng() {
		return isRainLng;
	}

	public void setIsRainLng(double isRainLng) {
		this.isRainLng = isRainLng;
	}
	
	
}
