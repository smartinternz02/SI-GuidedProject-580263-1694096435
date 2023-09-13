package com.example.assignment_three

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppComponent()
        }
    }
}

@Composable
fun MyAppComponent() {

    data class RadioButtonOption(
        val url: String,
        val label: String,
        val textColor: Color
    )

    var selectedUrl by remember { mutableStateOf("") }
    val context = LocalContext.current
    val radioOptions = listOf(
        RadioButtonOption("https://www.netflif.com" ,"Netflix" ,Color.Red),
        RadioButtonOption("https://www.youtube.com" ,"YouTube" , Color.Red),
        RadioButtonOption("https://www.apple.com" ,"Apple" , Color.Red),
        RadioButtonOption("https://www.amazon.com" ,"Amazon" ,Color.Red),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(30.dp),
                text = "Choose any option to visit the website",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                ),
            )

            Column(
                modifier = Modifier
                    .width(350.dp)
                    .height(250.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {

                radioOptions.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (selectedUrl == option.url),
                                onClick = {
                                    selectedUrl = option.url
                                }
                            )
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (selectedUrl == option.url),
                            onClick = {
                                selectedUrl = option.url
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = option.label, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = option.textColor)
                    }
                }
            }
        }
        Button(
            onClick = {
                if (selectedUrl.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedUrl))
                    context.startActivity(intent)
                } else {

                }
            },
            modifier = Modifier
                .padding(top = 700.dp)
                .fillMaxWidth()

        ) {
            Text("Submit")
        }
    }
}
