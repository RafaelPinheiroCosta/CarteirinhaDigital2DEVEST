package com.rafaelcosta.carteirinhadigital2devest.feature.carteirinha.presetantion.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun ValueText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 35.sp,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = Color.Black,
        fontWeight = fontWeight,
        modifier = modifier
    )
}