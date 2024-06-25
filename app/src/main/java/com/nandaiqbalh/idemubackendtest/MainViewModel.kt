package com.nandaiqbalh.idemubackendtest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class MainViewModel : ViewModel() {

	var workHoursState by mutableStateOf("")
	var hourlyRateState by mutableStateOf("")
	var salaryState by mutableStateOf("")
	var isLoading by mutableStateOf(false)

	fun onWorkHoursChanged(newString: String) {
		workHoursState = newString
	}

	fun onHourlyRateChanged(newString: String) {
		hourlyRateState = newString
	}

	fun calculateSalary() {
		viewModelScope.launch {
			isLoading = true
			delay(2000) // Penundaan selama 2 detik
			val workHours = workHoursState.toLongOrNull() ?: 0L
			val hourlyRate = hourlyRateState.toDoubleOrNull() ?: 0.0
			val regularHours = 40L
			val overtimeRate = 1.5

			val overtimeHours = if (workHours > regularHours) workHours - regularHours else 0L
			val regularPay = if (workHours > regularHours) regularHours * hourlyRate else workHours * hourlyRate
			val overtimePay = overtimeHours * hourlyRate * overtimeRate

			salaryState = (regularPay + overtimePay).toLong().toString()
			isLoading = false
		}
	}
}
