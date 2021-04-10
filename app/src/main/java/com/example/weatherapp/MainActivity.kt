package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.ui.main.MainFragment
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.week_fragment.*
import com.example.weatherapp.WeekFragment

import org.json.JSONArray
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*


//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
//
////////////////////////////////////////////////////////////////////////////////////////////////////////
//        // Instantiate the RequestQueue.
//        var requestQueue = Volley.newRequestQueue(this)
//        val API: String = "7d1dda002c1e24a974db25206c92f587"
//        val CITY: String = "Westland,MI,US"
//        val url = "https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API"
//
//        // Request a string response from the provided URL.
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
//            Response.Listener { response ->
//                // Display the first 500 characters of the response string.
//                // currentTemp.text = "Response is: ${response.substring(0, 500)}"
//
//                // this prints the WHOLE string
//                Log.i("JSON response", response.toString());
//
//                val main = response.getJSONObject("main")
//                val sys = response.getJSONObject("sys")
//                val wind = response.getJSONObject("wind")
//                val weather = response.getJSONArray("weather").getJSONObject(0)
//
//                Log.i("JSON Weather response", weather.toString());
//
//                val updatedAt:Long = response.getLong("dt")
//                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
//                    Date(updatedAt*1000)
//                )
//                val temp = main.getString("temp")+"°C"
//                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
//                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
//                val pressure = main.getString("pressure")
//                val humidity = main.getString("humidity")
//                val sunrise:Long = sys.getLong("sunrise")
//                val sunset:Long = sys.getLong("sunset")
//                val windSpeed = wind.getString("speed")
//                val weatherDescription = weather.getString("description")
//
//                // name of the location
//                val address = response.getString("name")+", "+sys.getString("country")
//
//                print(temp);
//                currentTemp.text = temp
//                location.text = address
//            },
//            Response.ErrorListener { currentTemp.text = "That didn't work!" })
//
//        // Access the RequestQueue through your singleton class.
//        requestQueue.add(jsonObjectRequest)
////////////////////////////////////////////////////////////////////////////////////////////////////////
//    }
//}



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeekFragment.newInstance())
                .commitNow()
        }

//////////////////////////////////////////////////////////////////////////////////////////////////////
        // Instantiate the RequestQueue.
        var requestQueue = Volley.newRequestQueue(this)
        val API: String = "7d1dda002c1e24a974db25206c92f587"
        val CITY: String = "Westland,MI,US"
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=$CITY&appid=$API"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                // Display the first 500 characters of the response string.
                // currentTemp.text = "Response is: ${response.substring(0, 500)}"

                // this prints the WHOLE string
                Log.i("JSON response", response.toString());

                var list1 = response.getJSONArray("list").getJSONObject(0)
                Log.i("LIST response", list1.toString());

                var main1 = list1.getJSONObject("main")
                Log.i("MAIN response", main1.toString());

                val temp1 = (main1.getString("temp")).toFloat()
                val KelvinToCelcius = ((temp1 - 273.15).toInt()).toString() +"°C"
                Log.i("TEMP response", KelvinToCelcius.toString());

                val time1 = list1.getString("dt_txt")
                Log.i("DATE response", time1.toString());

                val onlydate1 = time1.split(" ")[0]
                Log.i("DATE response", onlydate1.toString());


                val location = response.getJSONObject("city")
                val city = location.getString("name")
                val countryName = location.getString("country")

                cityName.text = city + ", " + countryName

            },
            { cityName.text = "Didn't work" })

        // Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest)
//////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}