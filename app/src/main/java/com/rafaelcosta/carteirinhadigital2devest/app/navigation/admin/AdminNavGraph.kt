package com.rafaelcosta.carteirinhadigital2devest.app.navigation.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.GraphRoutes
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.ProfileGuard
import com.rafaelcosta.carteirinhadigital2devest.app.ui.AppShell
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.GestaoSolicitacoesViewModel
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.factory.GestaoSolicitacoesViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.screen.GestaoSolicitacoesScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.repository.GestaoSolicitacoesRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.*
import kotlinx.coroutines.flow.StateFlow

fun NavGraphBuilder.adminNavGraph(
    navController: NavHostController,
    usuarioLogadoFlow: StateFlow<UsuarioLogado?>,
    gestaoSolicitacoesRepository: GestaoSolicitacoesRepository,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit
) {
    navigation(startDestination = "admin/home", route = GraphRoutes.ADMIN) {
        composable("admin/home") {
            ProfileGuard(usuarioFlow = usuarioLogadoFlow, perfilEsperado = PerfilAcesso.ADMIN,
                onSessaoAusente = onSessaoAusente, onPerfilInvalido = onPerfilInvalido) { usuario ->
                AppShell(title = "Administração", usuarioLogado = usuario,
                    canNavigateBack = false, darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange, onNavigateBack = {}, onLogout = onLogout) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text("Olá, ${usuario.nome}")
                        Text("Gerencie os pedidos de acesso de alunos e professores.")
                        Button(onClick = { navController.navigate("admin/solicitacoes") },
                            modifier = Modifier.fillMaxWidth()) {
                            Text("Gestão de Solicitações de Acesso")
                        }
                    }
                }
            }
        }
        composable("admin/solicitacoes") {
            ProfileGuard(usuarioFlow = usuarioLogadoFlow, perfilEsperado = PerfilAcesso.ADMIN,
                onSessaoAusente = onSessaoAusente, onPerfilInvalido = onPerfilInvalido) { usuario ->
                val factory = remember(gestaoSolicitacoesRepository) {
                    GestaoSolicitacoesViewModelFactory(repository = gestaoSolicitacoesRepository)
                }
                val vm: GestaoSolicitacoesViewModel = viewModel(factory = factory)
                AppShell(title = "Solicitações de acesso", usuarioLogado = usuario,
                    canNavigateBack = true, darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange, onNavigateBack = { navController.popBackStack() }, onLogout = onLogout) { padding ->
                    GestaoSolicitacoesScreen(vm, Modifier.fillMaxSize().padding(padding))
                }
            }
        }
    }
}
