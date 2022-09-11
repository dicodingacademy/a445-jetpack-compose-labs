package com.dicoding.mytemperatureconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dicoding.mytemperatureconverter.ui.theme.MyTemperatureConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTemperatureConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        StatefulTemperatureInput()
                        ConverterApp()
                        TwoWayConverterApp()
                    }
                }
            }
        }
    }
}

@Composable
fun StatefulTemperatureInput() {
    var input by rememberSaveable { mutableStateOf("") }
    var output by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Stateful Converter", style = MaterialTheme.typography.h5)
        OutlinedTextField(
            value = input,
            label = { Text("Enter temperature in Celsius") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { newInput ->
                input = newInput
                output = convertToFahrenheit(newInput)
            },
        )
        Text("Temperature in Fahrenheit : $output")
    }
}

private fun convertToFahrenheit(celsius: String) =
    celsius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()

@Composable
fun StatelessTemperatureInput(input: String, output: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Stateless Converter", style = MaterialTheme.typography.h5)
        OutlinedTextField(
            value = input,
            label = { Text("Enter temperature in Celsius") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
        Text("Temperature in Fahrenheit : $output")
    }
}

@Composable
private fun ConverterApp() {
    var input by rememberSaveable { mutableStateOf("") }
    var output by rememberSaveable { mutableStateOf("") }
    Column {
        StatelessTemperatureInput(
            input = input,
            output = output,
            onValueChange = { newInput ->
                input = newInput
                output = convertToFahrenheit(newInput)
            }
        )
    }
}

enum class Scale(val scaleName: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}

@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = input,
            label = { Text("Enter temperature in ${scale.scaleName}") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
    }
}

@Composable
private fun TwoWayConverterApp() {
    var celsius by rememberSaveable { mutableStateOf("") }
    var fahrenheit by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Two Way Converter", style = MaterialTheme.typography.h5)
        GeneralTemperatureInput(
            scale = Scale.CELSIUS,
            input = celsius,
            onValueChange = { newInput ->
                celsius = newInput
                fahrenheit = convertToFahrenheit(newInput)
            }
        )
        GeneralTemperatureInput(
            scale = Scale.FAHRENHEIT,
            input = fahrenheit,
            onValueChange = { newInput ->
                fahrenheit = newInput
                celsius = convertToCelsius(newInput)
            }
        )
    }
}

private fun convertToCelsius(fahrenheit: String) =
    fahrenheit.toDoubleOrNull()?.let {
        (it - 32) * 5 / 9
    }.toString()