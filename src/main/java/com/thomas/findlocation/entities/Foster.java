package com.thomas.findlocation.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foster")
public class Foster implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String first;
	private String last;
	private String phoneNumber;
	private String typeOfAnimal;
	private String moreDetails;
	private String address;
	private String region;
	private String file;
	private String[] files;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public void setFile() {
		files[0]  = file;
	}

	public String[] getFiles() {
		
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public String getFirstName() {
		return first;
	}

	public void setFirstName(String firstName) {
		this.first = firstName;
	}

	public String getLastName() {
		return last;
	}

	public void setLastName(String lastName) {
		this.last = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTypeOfAnimal() {
		return typeOfAnimal;
	}

	public void setTypeOfAnimal(String typeOfAnimal) {
		this.typeOfAnimal = typeOfAnimal;
	}

	public String getDetails() {
		return moreDetails;
	}

	public void setDetails(String details) {
		this.moreDetails = details;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Foster [id=" + id + ", firstName=" + first + ", lastName=" + last + ", phoneNumber="
				+ phoneNumber + ", typeOfAnimal=" + typeOfAnimal + ", details=" + moreDetails + ", address=" + address
				+ ", region=" + region + "]";
	}

}
