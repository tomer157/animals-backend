package com.thomas.findlocation.entities;

public class TempObject {
	double lat;
	double lng;

	public TempObject(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public TempObject() {

	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}
