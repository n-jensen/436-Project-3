package com.example.weatherapp

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.week_fragment.*

class WeekFragment : Fragment() {

    companion object {
        fun newInstance() = WeekFragment()
    }

    // implements the ToolbarListener interface must also implement a
    // callback method named onButtonClick which, in turn, accepts a String as argument.
    var activityCallback: FiveDaysWeatherListener? = null

    interface FiveDaysWeatherListener {
        fun setFiveDaysWeather(text: String)
    }

    // method is called automatically when the fragment has been initialized and associated with an
    // activity. The method is passed a reference to the activity in which the fragment is contained. The method must
    // store a local reference to this activity and verify that it implements the ToolbarListener interface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as FiveDaysWeatherListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement FiveDaysListener"
            )
        }
    }

    private lateinit var viewModel: WeekViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.week_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeekViewModel::class.java)
        // TODO: Use the ViewModel
        val CITY: String = "Westland,MI,US"
        activityCallback?.setFiveDaysWeather(CITY.toString())
    }

    fun getDayOneWeather(input1: String, input2: String) {
        dayOne.text = input1
        tempOne.text = input2
    }

    fun getDayTwoWeather(input1: String, input2: String ) {
        dayTwo.text = input1
        tempTwo.text = input2
    }

    fun getDayThreeWeather(input1: String, input2: String ) {
        dayThree.text = input1
        tempThree.text = input2
    }

    fun getDayFourWeather(input1: String, input2: String ) {
        dayFour.text = input1
        tempFour.text = input2
    }

    fun getDayFiveWeather(input1: String, input2: String ) {
        dayFive.text = input1
        tempFive.text = input2
    }

    fun error(input: String) {
        dayOne.text = input
        dayTwo.text = input
        dayThree.text = input
        dayFour.text = input
        dayFive.text = input

    }
}