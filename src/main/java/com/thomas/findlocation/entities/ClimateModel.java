package com.thomas.findlocation.entities;

public class ClimateModel {
	double maxTempLat = 0;
	double maxTempLng = 0;

	double minTempLat = 0;
	double minTempLng = 0;

	double isRainLat = 0;
	double isRainLng = 0;

	public ClimateModel(double maxTempLat, double maxTempLng, double minTempLat, double minTempLng, double isRainLat,
			double isRainLng) {
		super();
		this.maxTempLat = maxTempLat;
		this.maxTempLng = maxTempLng;
		this.minTempLat = minTempLat;
		this.minTempLng = minTempLng;
		this.isRainLat = isRainLat;
		this.isRainLng = isRainLng;
	}
	
	public ClimateModel() {
		
	}
	
	public double getMaxTempLat() {
		return maxTempLat;
	}

	public void setMaxTempLat(double maxTempLat) {
		this.maxTempLat = maxTempLat;
	}

	public double getMaxTempLng() {
		return maxTempLng;
	}

	public void setMaxTempLng(double maxTempLng) {
		this.maxTempLng = maxTempLng;
	}

	public double getMinTempLat() {
		return minTempLat;
	}

	public void setMinTempLat(double minTempLat) {
		this.minTempLat = minTempLat;
	}

	public double getMinTempLng() {
		return minTempLng;
	}

	public void setMinTempLng(double minTempLng) {
		this.minTempLng = minTempLng;
	}

	public double getIsRainLat() {
		return isRainLat;
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
