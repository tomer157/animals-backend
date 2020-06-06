package com.thomas.findlocation.entities;



	public class CityEntity {
		String name;
		double maxTemp;

		public CityEntity(String name, double maxTemp) {
			super();
			this.name = name;
			this.maxTemp = maxTemp;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getMaxTemp() {
			return maxTemp;
		}

		public void setMaxTemp(double maxTemp) {
			this.maxTemp = maxTemp;
		}

		@Override
		public String toString() {
			return "CityEntity [name=" + name + ", maxTemp=" + maxTemp + "]";
		}

	}


