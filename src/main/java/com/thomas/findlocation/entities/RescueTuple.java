package com.thomas.findlocation.entities;

import java.io.Serializable;

public class RescueTuple  implements Serializable{
	public String animal_Description;
	public String file;

	public RescueTuple() {

	}

	public RescueTuple(String animal_Description, String file) {
		super();
		this.animal_Description = animal_Description;
		this.file = file;
	}

	public String getAnimal_Description() {
		return animal_Description;
	}

	public void setAnimal_Description(String animal_Description) {
		this.animal_Description = animal_Description;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
