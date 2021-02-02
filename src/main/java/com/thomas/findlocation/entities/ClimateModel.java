package com.thomas.findlocation.entities;

import java.util.ArrayList;
import java.util.List;

//import javafx.util.Pair;

public class ClimateModel {
	double maxTempLat = 0;
	double maxTempLng = 0;

	double minTempLat = 0;
	double minTempLng = 0;
	List<TempObject> pairList = new ArrayList<>();
	List<TempObject> pairListMin = new ArrayList<>();

	List<RainObject> listofRain = new ArrayList<RainObject>();
	List<WindObject> listOfWinds = new ArrayList<WindObject>();

	public ClimateModel(double maxTempLat, double maxTempLng, double minTempLat, double minTempLng,
			List<RainObject> list, List<WindObject> windList) {
		super();
		this.maxTempLat = maxTempLat;
		this.maxTempLng = maxTempLng;
		this.minTempLat = minTempLat;
		this.minTempLng = minTempLng;
		this.listofRain = list;
		this.listOfWinds = windList;

	}

	public ClimateModel() {

	}

	public double getMaxTempLat() {
		return maxTempLat;
	}

	public List<TempObject> getPairListMin() {
		return pairListMin;
	}

	public void setPairListMin(List<TempObject> pairListMin) {
		this.pairListMin = pairListMin;
	}

	public List<TempObject> getPairList() {
		return pairList;
	}

	public void setPairList(List<TempObject> list) {
		this.pairList = list;
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

	public List<WindObject> getListOfWinds() {
		return listOfWinds;
	}

	public void setListOfWind(List<WindObject> listofWind) {
		this.listOfWinds = listofWind;
	}

}
