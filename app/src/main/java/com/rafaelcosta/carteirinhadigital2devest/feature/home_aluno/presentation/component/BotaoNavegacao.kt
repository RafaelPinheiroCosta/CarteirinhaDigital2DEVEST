package com.rafaelcosta.carteirinhadigital2devest.feature.home_aluno.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BotaoNavegacao (
    text : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = text
        )
    }
}
@Preview(showBackground = true)
@Composable
fun BotaoNavegacaoPreview(){
    BotaoNavegacao(
        text = "Carteirinha",
        onClick = {}
    )
}