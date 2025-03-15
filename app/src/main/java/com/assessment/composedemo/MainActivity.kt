package com.assessment.composedemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessment.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DemoText(message: String, fontSize: Float){
    Text(text = message,
         fontSize = fontSize.sp,
         fontWeight = FontWeight.Bold )
}

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit){
    Slider(modifier = Modifier.padding(10.dp),
           valueRange = 20f..38f,
           value = sliderPosition,
           onValueChange = {onPositionChange(it)})
}

@Composable
fun DemoScreen(modifier: Modifier = Modifier){
    val sliderPosition = remember { mutableFloatStateOf(30f) }
    val handlePositionChange = { position: Float -> (sliderPosition.floatValue) = position }

    Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {
        
        DemoText(message = "Welcome to Compose", fontSize = sliderPosition.floatValue)
        Spacer(modifier = Modifier.height(100.dp))
        DemoSlider(sliderPosition = sliderPosition.floatValue, onPositionChange = handlePositionChange)
        Text(style = MaterialTheme.typography.headlineMedium,
             text = sliderPosition.floatValue.toInt().toString() + "sp")
    }
}
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE, showSystemUi = true
)
@Composable
fun DemoTextPreview(){
    ComposeDemoTheme {
        DemoScreen()
    }
}