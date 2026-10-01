package com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafaelcosta.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.LoginEvent
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.LoginUiState

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEvent:(LoginEvent)-> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            10.dp,
            Alignment.CenterVertically
        ),
        modifier =modifier
    ) {
        Text(
            text = "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = uiState.usuario,
            onValueChange = {
                onEvent(LoginEvent.OnUsuarioChange(it))
            },
            isError = uiState.credentialError,
            label = {Text(text = "Usuario")
            }

        )
        TextField(
            value = uiState.senha,
            onValueChange = {
                onEvent(LoginEvent.OnSenhaChange(it))
            },
            isError = uiState.credentialError,
            label = {Text(text = "Senha")}
        )
        uiState.errorMessage?.let { error ->
            Text(
                text = error,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth(0.85f)
            )
        }
        Button(
            onClick = {
               onEvent(LoginEvent.OnEntrarClick)
            },
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(size = 4.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color.Black
            ),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .fillMaxWidth(.6f)
        ) {
            if(uiState.isLoading){
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(4.dp),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.35f)
                )
            }else{
                Text(
                    "Entrar",
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentPreview() {
    CarteirinhaDigital2DEVESTTheme {
        LoginContent(
            modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState(
                usuario = "aluno",
                senha = "123"
            ),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, name = "Login Error")
@Composable
private fun LoginContentErrorPreview() {
    CarteirinhaDigital2DEVESTTheme {
        LoginContent(
            modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState(
                usuario = "aluno",
                senha = "000",
                errorMessage = "Login ou senha inv├ílidos",
                credentialError = true
            ),
            onEvent = {}
        )
    }
}
