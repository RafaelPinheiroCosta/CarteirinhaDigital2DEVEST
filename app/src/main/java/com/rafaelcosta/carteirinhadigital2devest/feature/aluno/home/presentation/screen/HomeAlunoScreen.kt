package com.rafaelcosta.carteirinhadigital2devest.feature.aluno.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rafaelcosta.carteirinhadigital2devest.core.designsystem.component.NavigationButton
import com.rafaelcosta.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.PerfilAcesso
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado

@Composable
fun HomeAlunoScreen(
    usuarioLogado: UsuarioLogado,
    onCarteirinhaClick: () -> Unit,
    onUnidadesCurricularesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement =
            Arrangement.spacedBy(24.dp),
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Text(
            text = "Olá, ${usuarioLogado.nome}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = usuarioLogado.curso,
                    style =
                        MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Turma: ${usuarioLogado.turma}"
                )

                Text(
                    text =
                        "Matrícula: ${usuarioLogado.matricula}"
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement =
                Arrangement.spacedBy(14.dp)
        ) {
            NavigationButton(
                text = "Carteirinha",
                onClick = onCarteirinhaClick,
                modifier = Modifier.fillMaxWidth()
            )

            NavigationButton(
                text = "Unidades Curriculares",
                onClick = onUnidadesCurricularesClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomeAlunoScreenPreview() {
    CarteirinhaDigital2DEVESTTheme {
        HomeAlunoScreen(
            usuarioLogado = UsuarioLogado(
                id = "1",
                nome = "Aluno Modelo",
                matricula = "2026000001",
                perfil = PerfilAcesso.ALUNO,
                curso = "Desenvolvimento de Sistemas",
                turma = "2DEVEST-A",
                token = "token"
            ),
            onCarteirinhaClick = {},
            onUnidadesCurricularesClick = {}
        )
    }
}