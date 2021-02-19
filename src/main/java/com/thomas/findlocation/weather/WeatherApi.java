package com.thomas.findlocation.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.NewParentTypeMunger;

import org.json.JSONArray;
import org.json.JSONObject;

//import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;
import com.thomas.findlocation.entities.CityEntity;
import com.thomas.findlocation.entities.ClimateModel;
import com.thomas.findlocation.entities.RainObject;
import com.thomas.findlocation.entities.TempObject;
import com.thomas.findlocation.entities.WindObject;

//import javafx.util.Pair;

import java.net.*;

public class WeatherApi {

	public static HttpURLConnection connection;
	public static List<String> list1 = new ArrayList<>();
	public static List<String> list2 = new ArrayList<>();
	public static List<String> list3 = new ArrayList<>();
	public static List<String> list4 = new ArrayList<>();
	public static List<String> list5 = new ArrayList<>();
	public static ClimateModel climateModel = new ClimateModel();
	private static List<RainObject> rainList = new ArrayList<RainObject>();
	private static List<WindObject> windList = new ArrayList<WindObject>();
	public static JSONObject jo = null;
	private static List<CityEntity> cityList = new ArrayList<>();
	private static List<TempObject> listTemp = new ArrayList<>();
	private static List<TempObject> listTempMin = new ArrayList<>();

	////////////////////// min_temp ,max //////cities with rain array
//		public static Triplet<String, String, List<String>> tripletOne = new Triplet<String, String, List<String>>("", "",
//		
	static JSONObject js = new JSONObject();

	private static double firstMin_temp = 9.0;
	private static double firstMax_temp = 30.0;
	private static String selectedCity = null;
	private static String population = null;
	public static String city = "New York";
	public static String cityName = null;

//		public static StringBuffer responseContent = new StringBuffer();

	public static void main(String[] args) throws InterruptedException {
		parseWith();

	}

	public static ClimateModel parse(String responseBody) throws InterruptedException {

		try {

			jo = new JSONObject(responseBody);

			JSONObject cityOBJ = jo.getJSONObject("city");

			cityName = cityOBJ.get("name").toString();
			population = cityOBJ.get("population").toString();

			JSONArray listArray = jo.getJSONArray("list");
			for (int i = 0; i < listArray.length(); i++) {
				JSONObject listObject = listArray.getJSONObject(i);
				JSONObject main = listObject.getJSONObject("main");

				JSONObject weather = listObject.getJSONArray("weather").getJSONObject(0);
				String weatherMain = weather.getString("main");
				String weatherDesc = weather.getString("description");

				JSONObject rain = new JSONObject();
				if (listObject.has("rain")) {

					rain = listObject.getJSONObject("rain");
				}

				JSONObject wind = new JSONObject();
				JSONObject windObject = listObject.getJSONObject("wind");
				if (windObject.getInt("speed") >= 6) {

					wind = listObject.getJSONObject("wind");
				}

				CityEntity CE = new CityEntity(cityName, main.getDouble("temp_max"));

				cityList.add(new CityEntity(cityName, main.getDouble("temp_max")));

				if (firstMax_temp < main.getDouble("temp_max")) {

					selectedCity = cityName;
					String pop = population;
					double lat = (double) cityOBJ.getJSONObject("coord").get("lat");

					double lng = (double) cityOBJ.getJSONObject("coord").get("lon");

					climateModel.setMaxTempLat(lat);
					climateModel.setMaxTempLng(lng);

					TempObject tempObj = new TempObject(lat, lng);
					listTemp.add(tempObj);

				}
				if (firstMin_temp > main.getDouble("temp_min")) {

					double lat = (double) cityOBJ.getJSONObject("coord").get("lat");

					double lng = (double) cityOBJ.getJSONObject("coord").get("lon");

					climateModel.setMinTempLat(lat);
					climateModel.setMinTempLng(lng);
					TempObject tempObj = new TempObject(lat, lng);
					listTempMin.add(tempObj);

				}

				if (!rain.isEmpty()) {
					double lat = (double) cityOBJ.getJSONObject("coord").get("lat");

					double lng = (double) cityOBJ.getJSONObject("coord").get("lon");

					RainObject r = new RainObject(lat, lng);
					rainList.add(r);
				}

				if (!wind.isEmpty()) {
					double lat = (double) cityOBJ.getJSONObject("coord").get("lat");

					double lng = (double) cityOBJ.getJSONObject("coord").get("lon");

					WindObject w = new WindObject(lat, lng);
					windList.add(w);

				}

			}
			climateModel.setListofRain(rainList);
			climateModel.setListOfWind(windList);
			climateModel.setPairList(listTemp);
			climateModel.setPairListMin(listTempMin);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return climateModel;

	}

	public static ClimateModel parseWith() throws InterruptedException {
		BufferedReader reader;
		String line;
		String[] cities = { "Tel aviv", "Eilat", "ramat gan", "Rehovot", "qiryat shemona", "haifa", "nablus", "jericho",
				"netanya", "tiberias", "jerusalem", "beit she'an", "acre", "afula", "arad", "arraba", "ashdod",
				"Ashkelon", "Baqa al-Gharbiyye", "Bat Yam", "Beersheba", "Beit Shemesh", "Bnei Brak", "Dimona", "El'ad",
				"Giv'at Shmuel", "Givatayim", "Hadera", "Herzliya", "Hod HaSharon", "Holon", "Kafr Qasim", "Karmiel",
				"Kfar Saba", "Kfar Yona", "Kiryat Ata", "Kiryat Bialik", "Kiryat Gat", "Kiryat Malakhi", "Lod",
				"Ma'alot-Tarshiha", "migdal", "modin", "Nahariya", "Netivot", "Umm al-Fahm", "sde boker",
				"Mitzpe Ramon", "Nitzana", "hod hasharon", "bat yam","ashkelon" };
		ClimateModel c = new ClimateModel();

		try {
			for (int k = 0; k < cities.length; k++) {
				jo = null;
				StringBuffer responseContent = new StringBuffer();

				URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cities[k]
						+ "&hourly&units=metric&cnt=1&appid=a3f20118eead94533603b9665190dd88");
				connection = (HttpURLConnection) url.openConnection();

				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);

				int status = connection.getResponseCode();

				if (status > 299) {
					reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					while ((line = reader.readLine()) != null) {
						responseContent.append(line);

					}

				} else {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = reader.readLine()) != null) {
						responseContent.append(line);
					}

					reader.close();

				}

				c = parse(responseContent.toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

		return c;

	}

}
