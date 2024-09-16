package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter(){
    var inputvalue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var outputConversionFactor by remember { mutableStateOf(1.0) }
    var inputConversionFactor by remember { mutableStateOf(0.01) }

    val custumTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 40.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )

    fun getConvertedValue(){
        var inputValueAsDouble = inputvalue.toDoubleOrNull() ?:0.0
        var result = (inputValueAsDouble*inputConversionFactor*outputConversionFactor)
        println(inputValueAsDouble)
//        println("OutputConversionFactor: "+outputConversionFactor)
//        println("InputConversionFactor: "+inputConversionFactor)
        outputValue = "%.3f".format(result).toString()+ " "+ outputUnit
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Convertor", style = custumTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange = {
            inputvalue = it
            getConvertedValue()
        },
            label = { Text("Enter a number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
//            var dropDownState1 by remember { mutableStateOf(false)}
//            var dropDownState2 by remember { mutableStateOf(false)}
//            var dropDownClickedOutside by remember { mutableStateOf(true)}
            Box(){
                val context = LocalContext.current
                Button(onClick = {
//                    Toast.makeText(context, "Thanks for clicking" , Toast.LENGTH_LONG).show()
//                    dropDownState1 = true
                    iExpanded = true

                }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, "Arrow Drop Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {
//                    dropDownState1 = false
                    iExpanded = false
                }) {
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        iExpanded = false
                        inputConversionFactor=1.0
                        inputUnit = "Meters"
                        getConvertedValue()
                    })
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        iExpanded = false
                        inputConversionFactor = 0.01
                        getConvertedValue()
                        inputUnit = "Centimeters"
                    })
                    DropdownMenuItem(text = { Text("feet") }, onClick = {
                        iExpanded = false
                        inputConversionFactor = 0.3048
                        getConvertedValue()
                        inputUnit ="feet"
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        iExpanded = false
                        inputConversionFactor = (0.001)
                        getConvertedValue()
                        inputUnit = "Millimeters"
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(){
                val context = LocalContext.current
                Button(onClick = {
//                    Toast.makeText(context, "Thanks for clicking" , Toast.LENGTH_LONG).show()
//                    dropDownState2 = true
                    oExpanded = true
                }){
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, "Arrow Drop Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {
//                    dropDownState2 = false
                    oExpanded = false
                }) {
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        outputConversionFactor=1.0
                        getConvertedValue()
                    })
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        outputConversionFactor=100.0
                        getConvertedValue()
                    })
                    DropdownMenuItem(text = { Text("feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "feet"
                        outputConversionFactor=3.2808
                        getConvertedValue()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeters"
                        outputConversionFactor=1000.0
                        getConvertedValue()
                    })
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${outputValue}",
            style = MaterialTheme.typography.headlineSmall)

    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}