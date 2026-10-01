package com.rafaelcosta.carteirinhadigital2devest.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rafaelcosta.carteirinhadigital2devest.app.di.AppContainer
import com.rafaelcosta.carteirinhadigital2devest.app.session.SessionViewModel
import com.rafaelcosta.carteirinhadigital2devest.app.session.SessionViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.app.ui.AppShell
import com.rafaelcosta.carteirinhadigital2devest.feature.carteirinha.presetantion.screen.CarteirinhaScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.home_aluno.presentation.screen.HomeScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.LoginViewModel
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.factory.LoginViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.screen.LoginScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    container: AppContainer
) {
    val sessionFactory = remember(container.sessionTokenStore) {
        SessionViewModelFactory(
            sessionTokenStore = container.sessionTokenStore
        )
    }

    val sessionViewModel: SessionViewModel = viewModel(factory = sessionFactory)
    val usuarioLogado by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()
    val usuario = usuarioLogado

    fun logout() {
        sessionViewModel.limparSessao()
        navController.navigate(Routes.Login.route) {
            popUpTo(Routes.HomeAluno.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            val loginFactory = remember(
                container.loginRepository
            ) {
                LoginViewModelFactory(
                    repository = container.loginRepository
                )
            }

            val loginViewModel: LoginViewModel = viewModel(factory = loginFactory)

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSucesso = { usuario ->
                    sessionViewModel.setUsuarioLogado(usuario)
                    navController.navigate(Routes.HomeAluno.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            Routes.HomeAluno.route
        ) {
            if (usuario == null) {
                RedirecionarParaLogin(navController)
            } else {
                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { },
                    onLogout = {
                        logout()
                    }
                ) { innerPadding ->
                    HomeScreen(
                        navController = navController,
                        usuarioLogado = usuario,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.Carteirinha.route) {
            if (usuario == null) {
                RedirecionarParaLogin(navController)
            } else {
                AppShell(
                    title = "Carteirinha",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = {
                        logout()
                    }
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

        composable(Routes.UCAluno.route) {
            if (usuario == null) {
                RedirecionarParaLogin(navController)
            } else {
                val ucFactory = remember(container.unidadeCurricularRepository) {
                    UnidadeCurricularViewModelFactory(
                        repository = container.unidadeCurricularRepository
                    )
                }
                val ucViewModel: UnidadeCurricularViewModel = viewModel(factory = ucFactory)

                AppShell(
                    title = "Unidades Curriculares",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = {
                        logout()
                    }
                ) { innerPadding ->
                    UnidadeCurricularScreen(
                        viewModel = ucViewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun RedirecionarParaLogin(
    navController:NavHostController
) {
    LaunchedEffect(Unit) {
        navController.navigate( Routes.Login.route) {
            popUpTo(Routes.HomeAluno.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}