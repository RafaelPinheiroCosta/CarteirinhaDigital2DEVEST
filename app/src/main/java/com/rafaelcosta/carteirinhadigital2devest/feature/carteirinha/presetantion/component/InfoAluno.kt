package com.rafaelcosta.carteirinhadigital2devest.feature.carteirinha.presetantion.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoAluno(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    fontSizeValue: TextUnit = 35.sp,
    fontWeightValue: FontWeight = FontWeight.Bold
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        LabelText(
            text = label,
            modifier = Modifier.weight(1f)
        )
        ValueText(
            text = value,
            fontSize = fontSizeValue,
            fontWeight = fontWeightValue,
            modifier = Modifier.weight(3f)
        )
    }
}