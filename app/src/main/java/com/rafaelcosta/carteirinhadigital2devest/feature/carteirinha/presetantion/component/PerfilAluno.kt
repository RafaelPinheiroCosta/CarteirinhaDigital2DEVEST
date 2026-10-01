package com.rafaelcosta.carteirinhadigital2devest.feature.carteirinha.presetantion.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafaelcosta.carteirinhadigital2devest.R

@Composable
fun PerfilAluno(
    nome: String,
    curso: String,
    idFoto: Int = R.drawable.login
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = idFoto),
            contentDescription = "Foto Perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    MaterialTheme.colorScheme.primary,
                    CircleShape
                )
        )
        InfoAluno(
            label = "Nome",
            value = nome
        )
        InfoAluno(
            label = "Curso",
            value = curso,
            fontSizeValue = 25.sp,
            fontWeightValue = FontWeight.Normal
        )
    }
}