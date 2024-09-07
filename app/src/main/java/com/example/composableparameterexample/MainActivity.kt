package com.example.composableparameterexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.composableparameterexample.ui.theme.ComposableParameterExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableParameterExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { // Don't do this at home
                        Button(onClick = {}) { Text(text = "Top bar button") }
                    },
                    floatingActionButton = { // Don't do this at home
                        Text(text = "Floating action button")
                    }

                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) { // Column is a composable that places its children in a vertical sequence
                        var switchState by remember { mutableStateOf(true) }
                        LabeledComposable(text = "On or Off") {
                            Switch(checked = switchState, onCheckedChange = { switchState = it })
                        }

                        var sliderValue by remember { mutableFloatStateOf(0f) }
                        LabeledComposable(text = "Rating") {
                            Slider(
                                value = sliderValue, onValueChange = { sliderValue = it },
                                valueRange = 1f..10f, steps = 8
                            )
                        }
                        Text(text = "Slider value: $sliderValue")
                    }
                    LabeledComposable(
                        modifier = Modifier.padding(innerPadding),
                        text = "Rating"
                    ) {
                        Switch(checked = true, onCheckedChange = {})
                    }
                }
            }
        }
    }
}

@Composable
fun LabeledComposable(
    text: String,
    modifier: Modifier = Modifier,
    content: @Composable (name: String) -> Unit
) {
    Column(modifier = modifier) {
        Text(text = text)
        content(name = "Anders")
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
        LabeledComposable(text = "Rating") {
            Switch(checked = true, onCheckedChange = {})
        }
    }
}
