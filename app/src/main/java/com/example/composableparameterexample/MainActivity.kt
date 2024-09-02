package com.example.composableparameterexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composableparameterexample.ui.theme.ComposableParameterExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableParameterExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(text = text)
        content()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
