package com.thomas.findlocation.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.NewParentTypeMunger;
import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;
import com.thomas.findlocation.entities.CityEntity;

import java.net.*;

public class WeatherApi {

	public static HttpURLConnection connection;
	public static List<String> list1 = new ArrayList<>();
	public static List<String> list2 = new ArrayList<>();
	public static List<String> list3 = new ArrayList<>();
	public static List<String> list4 = new ArrayList<>();
	public static List<String> list5 = new ArrayList<>();

	public static JSONObject jo = null;
	private static List<CityEntity> cityList = new ArrayList<>();

	////////////////////// min_temp ,max //////cities with rain array
//		public static Triplet<String, String, List<String>> tripletOne = new Triplet<String, String, List<String>>("", "",
//		
	static JSONObject js = new JSONObject();

	private static double firstMin_temp = 0;
	private static double firstMax_temp = 0;
	private static String selectedCity = null;
	private static String population = null;
	public static String city = "New York";
	public static String cityName = null;
	private static Pair<Double, Double> pair;

//		public static StringBuffer responseContent = new StringBuffer();

	public static void main(String[] args) throws InterruptedException {
		parseWith();

	}

	public static Pair parse(String responseBody) throws InterruptedException {

		try {
			System.out.println("res " + responseBody);

			System.out.println("jo1 " + jo);
			jo = new JSONObject(responseBody);

//		  System.out.println("jo "+jo);

//		    System.out.println("jo "+jo);
			JSONObject cityOBJ = jo.getJSONObject("city");
			System.out.println("cityobj " + cityOBJ);
			cityName = cityOBJ.get("name").toString();
			population = cityOBJ.get("population").toString();
			System.out.println(cityName);

			JSONArray listArray = jo.getJSONArray("list");
			for (int i = 0; i < listArray.length(); i++) {
				JSONObject listObject = listArray.getJSONObject(i);
				JSONObject main = listObject.getJSONObject("main");

				JSONObject weather = listObject.getJSONArray("weather").getJSONObject(0);
				String weatherMain = weather.getString("main");
				String weatherDesc = weather.getString("description");
				JSONObject rain = new JSONObject();
				if (listObject.has("rain")) {
					System.out.println("rain exiasts");
					rain = listObject.getJSONObject("rain");
				}

				System.out.println("city name ");
				System.out.println(main.get("temp_min") + "index " + i);
				System.out.println(main.get("temp_max") + "index of max " + i);

				CityEntity CE = new CityEntity(cityName, main.getDouble("temp_max"));

				cityList.add(new CityEntity(cityName, main.getDouble("temp_max")));

				if (firstMax_temp < main.getDouble("temp_max")) {
					firstMax_temp = main.getDouble("temp_max");
					selectedCity = cityName;
					String pop = population;
					double lat = (double) cityOBJ.getJSONObject("coord").get("lat");

					double lng = (double) cityOBJ.getJSONObject("coord").get("lon");

					System.out.println("lat  " + lat + "  " + lng);
					pair = new Pair<Double, Double>(lat, lng);
					System.out.println("pari " + pair.getValue0());
					// youll need selectedcity and firstmax

				}

//						firstMin_temp = main.getDouble("temp_min");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return pair;

	}
	
	
	
	
	public static Pair parseWith() throws InterruptedException {
		BufferedReader reader;
		String line;
		String[] cities = { "Tel aviv", "Eilat", "ramat gan" };
		Pair<Double, Double>newPair = new Pair<Double, Double>(null, null);
		

		try {
			for (int k = 0; k < cities.length; k++) {
				jo = null;
				StringBuffer responseContent = new StringBuffer();

				URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cities[k]
						+ "&hourly&units=metric&cnt=1&appid=a3f20118eead94533603b9665190dd88");
				connection = (HttpURLConnection) url.openConnection();
				System.out.println("city " + cities[k]);
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);

				int status = connection.getResponseCode();
				System.out.println(status);

				if (status > 299) {
					reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					while ((line = reader.readLine()) != null) {
						responseContent.append(line);

					}
					// reader.close();
				} else {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = reader.readLine()) != null) {
						responseContent.append(line);
					}

					// reader.close();

				}

				System.out.println(responseContent.toString());

				newPair = 	parse(responseContent.toString());
				System.out.println("new pair  "+newPair);

//				

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		
		return newPair;
		
		
	}

}
