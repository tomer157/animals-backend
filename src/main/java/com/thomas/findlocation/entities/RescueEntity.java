package com.thomas.findlocation.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "rescueEntity")
public class RescueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private double x_coord;
	private double y_coord;
	private String coord_description;
	private String Animal_description;
	private String[] file;
	private Status status;
	private String pickupDescription;

	private int severity;

	public RescueEntity(String id, double x_coord, double y_coord, String coord_description, String animal_description,
			String[] file, int severity, Status status,String pickupDesc) {
		super();
		this.id = id;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.coord_description = coord_description;
		Animal_description = animal_description;
		this.file = file;
		this.severity = severity;
		this.status = status;
		this.pickupDescription = pickupDesc;
	}
	
	public RescueEntity() {
		
	}
	
	

	public String getPickupDescription() {
		return pickupDescription;
	}

	public void setPickupDescription(String pickupDescription) {
		this.pickupDescription = pickupDescription;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getCoord_description() {
		return coord_description;
	}

	public void setCoord_description(String coord_description) {
		this.coord_description = coord_description;
	}

	public String getAnimal_description() {
		return Animal_description;
	}

	public void setAnimal_description(String animal_description) {
		Animal_description = animal_description;
	}

	public String[] getFile() {
		return file;
	}

	public void setFile(String[] file) {
		this.file = file;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

}
