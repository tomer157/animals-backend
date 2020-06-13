package com.thomas.findlocation.entities;

import java.util.ArrayList;
import java.util.List;

public class ClimateModel {
	double maxTempLat = 0;
	double maxTempLng = 0;

	double minTempLat = 0;
	double minTempLng = 0;

	List<RainObject> listofRain = new ArrayList<RainObject>();

	public ClimateModel(double maxTempLat, double maxTempLng, double minTempLat, double minTempLng,
			List<RainObject> list) {
		super();
		this.maxTempLat = maxTempLat;
		this.maxTempLng = maxTempLng;
		this.minTempLat = minTempLat;
		this.minTempLng = minTempLng;
		this.listofRain = list;

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

	public List<RainObject> getListofRain() {
		return listofRain;
	}

	public void setListofRain(List<RainObject> listofRain) {
		this.listofRain = listofRain;
	}

}
