package com.rafaelcosta.carteirinhadigital2devest.feature.home_aluno.presentation.screen

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.Routes
import com.rafaelcosta.carteirinhadigital2devest.feature.home_aluno.presentation.component.BotaoNavegacao

@Composable
fun HomeScreen(
    navController: NavController = NavController(
        LocalContext.current
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aluno",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BotaoNavegacao(
                "Carteirinha",
                {
                    navController.navigate(Routes.Carteirinha.route)
                },
                modifier = Modifier.fillMaxWidth(.7f)
            )
            BotaoNavegacao(
                "Unidades Curriculares",
                {
                    navController.navigate(Routes.UCAluno.route)
                },
                modifier = Modifier.fillMaxWidth(.7f)
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    )
}