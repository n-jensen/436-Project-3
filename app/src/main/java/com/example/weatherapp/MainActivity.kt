package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
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


class MainActivity : FragmentActivity(), WeekFragment.FiveDaysListener, MainFragment.ButtonClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onButtonClick(text: String) {
        // Instantiate the RequestQueue.
        var requestQueue = Volley.newRequestQueue(this)
        val API: String = "7d1dda002c1e24a974db25206c92f587"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$text&units=metric&appid=$API"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                // Display the first 500 characters of the response string.
                // currentTemp.text = "Response is: ${response.substring(0, 500)}"
                // this prints the WHOLE string
                Log.i("JSON response", response.toString());

                val main = response.getJSONObject("main")
                val sys = response.getJSONObject("sys")
                val wind = response.getJSONObject("wind")
                val weather = response.getJSONArray("weather").getJSONObject(0)

                Log.i("JSON Weather response", weather.toString());

                val updatedAt: Long = response.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                // name of the location
                val address = response.getString("name") + ", " + sys.getString("country")

                val textFragment = supportFragmentManager.findFragmentById(R.id.container) as MainFragment
                textFragment.changeTextProperties(address, temp)
            },
            {
                val errorMessage = "That didn't work!"
                val textFragment = supportFragmentManager.findFragmentById(R.id.container) as MainFragment
                textFragment.error(errorMessage)
            })

        // Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest)
    }

    override fun SetFiveDaysWeather(text: String) {
        // Instantiate the RequestQueue.
        var requestQueue = Volley.newRequestQueue(this)
        val API: String = "7d1dda002c1e24a974db25206c92f587"
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=$text&appid=$API"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                // Display the first 500 characters of the response string.
                // currentTemp.text = "Response is: ${response.substring(0, 500)}"
                // this prints the WHOLE string
                Log.i("JSON response", response.toString());

                var list1 = response.getJSONArray("list").getJSONObject(7)
                Log.i("LIST response", list1.toString());

                var main1 = list1.getJSONObject("main")
                Log.i("MAIN response", main1.toString());

                val temp1 = (main1.getString("temp")).toFloat()
                val KelvinToCelcius1 = ((temp1 - 273.15).toInt()).toString() + "°C"
                Log.i("TEMP response", KelvinToCelcius1.toString());

                val time1 = list1.getString("dt_txt")
                Log.i("DATE response", time1.toString());

                val onlydate1 = time1.split(" ")[0]
                Log.i("DATE response", onlydate1.toString());


                val location = response.getJSONObject("city")
                val city = location.getString("name")
                val countryName = location.getString("country")

                val Fragment2 =
                    supportFragmentManager.findFragmentById(R.id.container) as WeekFragment

                Fragment2.GetFiveDaysWeatherLocation(("$city, $countryName"))

                Fragment2.GetDayOneWeather(onlydate1, KelvinToCelcius1)
////////////////////////////////////////////////////////////////////////////////////////////////////

                var list2 = response.getJSONArray("list").getJSONObject(15)

                var main2 = list2.getJSONObject("main")

                val temp2 = (main2.getString("temp")).toFloat()
                val KelvinToCelcius2 = ((temp2 - 273.15).toInt()).toString() + "°C"

                val time2 = list2.getString("dt_txt")

                val onlydate2 = time2.split(" ")[0]

                Fragment2.GetDayTwoWeather(onlydate2, KelvinToCelcius2)

////////////////////////////////////////////////////////////////////////////////////////////////////

                var list3 = response.getJSONArray("list").getJSONObject(23)

                var main3 = list3.getJSONObject("main")

                val temp3 = (main3.getString("temp")).toFloat()
                val KelvinToCelcius3 = ((temp3 - 273.15).toInt()).toString() + "°C"

                val time3 = list3.getString("dt_txt")

                val onlydate3 = time3.split(" ")[0]

                Fragment2.GetDayThreeWeather(onlydate3, KelvinToCelcius3)

////////////////////////////////////////////////////////////////////////////////////////////////////

                var list4 = response.getJSONArray("list").getJSONObject(31)

                var main4 = list4.getJSONObject("main")

                val temp4 = (main4.getString("temp")).toFloat()
                val KelvinToCelcius4 = ((temp4 - 273.15).toInt()).toString() + "°C"

                val time4 = list4.getString("dt_txt")

                val onlydate4 = time4.split(" ")[0]

                Fragment2.GetDayFourWeather(onlydate4, KelvinToCelcius4)

////////////////////////////////////////////////////////////////////////////////////////////////////

                var list5 = response.getJSONArray("list").getJSONObject(39)

                var main5 = list5.getJSONObject("main")

                val temp5 = (main5.getString("temp")).toFloat()
                val KelvinToCelcius5 = ((temp5 - 273.15).toInt()).toString() + "°C"

                val time5 = list5.getString("dt_txt")

                val onlydate5 = time5.split(" ")[0]

                Fragment2.GetDayFiveWeather(onlydate5, KelvinToCelcius5)

            },
            {
                val errorMessage = "That didn't work!"
                val Fragment2 =
                    supportFragmentManager.findFragmentById(R.id.container) as WeekFragment
                Fragment2.error(errorMessage)
            })

        // Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest)
//////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
