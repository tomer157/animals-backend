package com.thomas.findlocation.entities;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity

@Table(name = "marker")
public class Marker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private double x_coord;
	private double y_coord;
	private String coord_description;
	private String Animal_description;
	private String[] file;
	

	private int severity;
	
	
	
	
	public String[] getFile() {
		return file;
	}

	public void setFile(String[] file) {
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getX_coord() {
		return x_coord;
	}

	public void setX_coord(double x_coord) {
		this.x_coord = x_coord;
	}

	public double getY_coord() {
		return y_coord;
	}

	public void setY_coord(double y_corrd) {
		this.y_coord = y_corrd;
	}

	public String getCoord_description() {
		return coord_description;
	}

	public void setCoord_description(String coord_description) {
		this.coord_description = coord_description;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getAnimal_description() {
		return Animal_description;
	}

	public void setAnimal_description(String animal_description) {
		Animal_description = animal_description;
	}

	@Override
	public String toString() {
		return "Marker [id=" + id + ", x_coord=" + x_coord + ", y_coord=" + y_coord + ", coord_description="
				+ coord_description + "]";
	}

}
