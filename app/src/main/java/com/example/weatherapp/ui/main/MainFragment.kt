package com.example.weatherapp.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.weatherapp.R
import com.example.weatherapp.WeekFragment
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    // implements the ToolbarListener interface must also implement a
    // callback method named onButtonClick which, in turn, accepts a String as argument.
    var activityCallback: CurrentWeatherListener? = null

    interface CurrentWeatherListener {
        fun setCurrentWeather(text: String)
    }

    // method is called automatically when the fragment has been initialized and associated with an
    // activity. The method is passed a reference to the activity in which the fragment is contained. The method must
    // store a local reference to this activity and verify that it implements the ToolbarListener interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as CurrentWeatherListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement ButtonClickedListener"
            )
        }
    }


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.main_fragment, container, false)
        // find the 7 day button
        val btn = v.findViewById<Button>(R.id.btn7day)
        // when the 7 day button is clicked, replace the current fragment with the 7 day fragment
        btn.setOnClickListener {
            val secondFragment = WeekFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.add(R.id.container, secondFragment)
            transaction?.commit()
        }
        return v;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        val CITY: String = "Westland,MI,US"
        activityCallback?.setCurrentWeather(CITY.toString())
    }

    // input variable will consist of the temperature
    fun getCurrentWeather(input: String) {
        currentTemp.text = input
    }

    // input variable will consist of the location
    fun getLocation(input: String) {
        location.text = input
    }

    // input variable will consist of the weather description
    fun getWeatherDesc(input: String) {
        mainDesc.text = input
    }

    // input variable will consist of the max min temperature
    fun getMinMaxTemp(input: String) {
        MaxMinTemp.text = input
    }

    // input variable will consist of the feels like temperature
    fun getFeelLikeTemp(input: String) {
        feelLikeTemp.text = input
    }

    // this function will print an error message, if the api calls fails
    fun error(input: String) {
        location.text = input
    }
}