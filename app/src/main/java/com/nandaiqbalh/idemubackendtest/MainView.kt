package com.nandaiqbalh.idemubackendtest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainView(
	viewModel: MainViewModel,
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		CustomTextField(
			label = "Work Hours",
			value = viewModel.workHoursState,
			onValueChanged = { viewModel.onWorkHoursChanged(it) }
		)

		Spacer(modifier = Modifier.height(10.dp))

		CustomTextField(
			label = "Hourly Rate",
			value = viewModel.hourlyRateState,
			onValueChanged = { viewModel.onHourlyRateChanged(it) }
		)

		Spacer(modifier = Modifier.height(10.dp))

		Button(
			onClick = {
				if (viewModel.workHoursState.isNotEmpty() && viewModel.hourlyRateState.isNotEmpty()) {
					viewModel.calculateSalary()
				}
			},
			modifier = Modifier.fillMaxWidth()
		) {
			Text(text = "Calculate Salary", color = Color.Whiten)
		}

		Spacer(modifier = Modifier.height(20.dp))

		if (viewModel.isLoading) {
			CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
		} else if (viewModel.salaryState.isNotEmpty()) {
			Text(text = "Salary: ${viewModel.salaryState}", fontSize = 20.sp, color = Color.Black)
		}
	}
}

@Composable
fun CustomTextField(
	label: String,
	value: String,
	onValueChanged: (String) -> Unit,
) {
	OutlinedTextField(
		value = value,
		onValueChange = onValueChanged,
		label = { Text(text = label, color = Color.Black) },
		modifier = Modifier.fillMaxWidth(),
		keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
		colors = OutlinedTextFieldDefaults.colors(
			focusedTextColor = Color.Black,
			cursorColor = Color.Black,
			focusedBorderColor = Color.Black,
		)
	)
}
