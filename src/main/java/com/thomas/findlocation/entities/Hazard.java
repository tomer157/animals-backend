package com.thomas.findlocation.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hazards")
public class Hazard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private double x_coord;
	private double y_coord;
	
	
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
	public void setY_coord(double y_coord) {
		this.y_coord = y_coord;
	}

	
	
}
