package com.example.composableparameterexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composableparameterexample.ui.theme.ComposableParameterExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableParameterExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    /*topBar = { // Don't do this at home
                        Button(onClick = {}) { Text(text = "Top bar button") }
                    },
                    floatingActionButton = { // Don't do this at home: Should be a floating action button
                        Text(text = "Floating action button")
                    }*/

                ) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun MainContent(modifier: Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var switchState by remember { mutableStateOf(true) }
        LabeledComposable(text = "On or Off") {
            Switch(checked = switchState, onCheckedChange = { switchState = it })
        }
        Text(text = "Switch state: $switchState")

        Spacer(modifier = Modifier.height(30.dp))

        var sliderValue by remember { mutableFloatStateOf(0f) }
        LabeledComposable(text = "Rating") {
            Slider(
                value = sliderValue, onValueChange = { sliderValue = it },
                valueRange = 1f..10f,
                steps = 8 // number of inner steps
            )
        }
        Text(text = "Slider value: ${sliderValue.toInt()}")

        var ok by remember { mutableStateOf(false) }
        LabeledComposable(text = "OK") {
            Checkbox(checked = ok, onCheckedChange = { ok = it })
        }
        Text(text = "Checkbox value: $ok")

        // Button on Button: Don't do this at home
        Button(onClick = { /*TODO*/ }) {
            Column {
                Text(text = "My text")
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                )
                { Text(text = "Submit") }
            }
        }
    }
}

@Composable
fun LabeledComposable(
    text: String,
    // TODO add location parameter
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = text)
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledComposableSliderPreview() {
    ComposableParameterExampleTheme {
        LabeledComposable(text = "Rating") {
            Slider(value = 0f, onValueChange = {})
        }
    }
}

@Preview
@Composable
fun LabeledComposableCheckBoxPreview() {
    ComposableParameterExampleTheme {
        LabeledComposable(
            text = "Do you like it?",
            content = {
                Switch(checked = true, onCheckedChange = {})
            }
        )
    }
}