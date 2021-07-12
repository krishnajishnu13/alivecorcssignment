package com.alivecor.alivecorassignment

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.Period
import java.util.*

class SharedViewModel : ViewModel() {
    var listMonths = listOf<String>()
    var listDays = mutableListOf<String>()
    var listYears = mutableListOf<String>()

    init {
        listMonths = listOf<String>("Jan", "Feb",
            "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec" )

        listDays = mutableListOf<String>()
        for(i in 1..31){
            listDays.add(i.toString())
            Log.i("SharedViewModel", listDays.get(i-1))
        }

        listYears = mutableListOf<String>()
        val thisYear = Calendar.getInstance().get(Calendar.YEAR)
        for(index in 1900..thisYear){
            listYears.add(index.toString())
        }
        for(i in 0..listYears.size-1){
            Log.i("SharedViewModel", listYears.get(i))
        }

    }

    private var _firstName = MutableLiveData("Krishna")
    val firstName: LiveData<String> = _firstName

    private var _secondName = MutableLiveData("Manju")
    val secondNam: LiveData<String> = _secondName

    private var _dateOfBirth = MutableLiveData<String>()
    val dateOfBirth: LiveData<String>
        get() = _dateOfBirth


    fun saveBioDetails(
        fName: String?,
        lName: String?
    ) {
        _firstName.value = fName
        _secondName.value = lName
    }

    @SuppressLint("NewApi")
    fun getAgeTest(sYear: Int, sMonth: Int, sDay: Int) {
        val today: LocalDate = LocalDate.now() //Today's date

        val birthday: LocalDate = LocalDate.of(sYear, sMonth, sDay) //Birth date


        val period: Period = Period.between(birthday, today)
        _dateOfBirth.value = StringBuffer().append(period.getYears()).append(" years, ")
                .append(period.getMonths()).append(" months, ")
                .append(period.getDays()).append(" days").toString()

    }
}