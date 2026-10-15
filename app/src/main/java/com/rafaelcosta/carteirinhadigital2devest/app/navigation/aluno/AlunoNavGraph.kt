package com.rafaelcosta.carteirinhadigital2devest.app.navigation.aluno

import androidx.compose.foundation.layout.fillMaxSize
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
import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.carteirinha.presentation.screen.CarteirinhaScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.home.presentation.screen.HomeAlunoScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.PerfilAcesso
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.presentation.UnidadeCurricularViewModel
import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.presentation.screen.UnidadeCurricularScreen
import kotlinx.coroutines.flow.StateFlow

fun NavGraphBuilder.alunoNavGraph(
    navController: NavHostController,
    usuarioLogadoFlow: StateFlow<UsuarioLogado?>,
    unidadeCurricularRepository: UnidadeCurricularRepository,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit
) {
    navigation(
        startDestination = AlunoRoutes.HOME,
        route = GraphRoutes.ALUNO
    ) {
        composable(
            route = AlunoRoutes.HOME
        ) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.ALUNO,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->

                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {},
                    onLogout = onLogout
                ) { innerPadding ->
                    HomeAlunoScreen(
                        usuarioLogado = usuario,
                        onCarteirinhaClick = {
                            navController.navigate(AlunoRoutes.CARTEIRINHA)
                        },
                        onUnidadesCurricularesClick = {
                            navController.navigate(AlunoRoutes.UNIDADES_CURRICULARES)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(route = AlunoRoutes.CARTEIRINHA) {

            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.ALUNO,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->

                AppShell(
                    title = "Carteirinha",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = onLogout
                ) { innerPadding ->
                    CarteirinhaScreen(
                        usuarioLogado = usuario,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(route = AlunoRoutes.UNIDADES_CURRICULARES) {

            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.ALUNO,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->

                val factory = remember(unidadeCurricularRepository) {
                    UnidadeCurricularViewModelFactory(
                        repository = unidadeCurricularRepository
                    )
                }

                val unidadeCurricularViewModel: UnidadeCurricularViewModel = viewModel(
                    factory = factory
                )
                AppShell(
                    title = "Unidades Curriculares",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = onLogout
                ) { innerPadding ->
                    UnidadeCurricularScreen(
                        viewModel = unidadeCurricularViewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}