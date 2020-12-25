package com.thomas.findlocation.entities;

public class WindObject {
	double isWindLat = 0;
	double isWindLng = 0;

	public WindObject() {
	}

	public double getIsWindLat() {
		return isWindLat;
	}

	public WindObject(double isWindLat, double isWindLng) {
		super();
		this.isWindLat = isWindLat;
		this.isWindLng = isWindLng;
	}

	public void setIsWindLat(double isWindLat) {
		this.isWindLat = isWindLat;
	}

	public double getIsWindLng() {
		return isWindLng;
	}

	public void setIsWindLng(double isWindLng) {
		this.isWindLng = isWindLng;
	}

}
