package com.rafaelcosta.carteirinhadigital2devest.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.rafaelcosta.carteirinhadigital2devest.feature.carteirinha.presetantion.screen.CarteirinhaScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.home_aluno.presentation.screen.HomeScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.screen.LoginScreen
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    container: AppContainer
) {

    val sessionFactory = remember(container.sessionTokenStore) {
        SessionViewModelFactory(
            sessionTokenStore = container.sessionTokenStore
        )
    }

    val sessionViewModel: SessionViewModel =
        viewModel(factory = sessionFactory)

    val usuarioLogado by
        sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()

    val usuario = usuarioLogado

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {

            LoginScreen(
                navController = navController,
                onLoginSucesso = { usuario ->

                    sessionViewModel.setUsuarioLogado(usuario)

                    navController.navigate(
                        Routes.HomeAluno.route
                    ) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.Carteirinha.route) {

            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

                CarteirinhaScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(Routes.HomeAluno.route) {

            if (usuario == null) {

                LaunchedEffect(Unit) {

                    navController.navigate(
                        Routes.Login.route
                    ) {
                        popUpTo(Routes.HomeAluno.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }

            } else {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    HomeScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.UCAluno.route) {

            if (usuario == null) {

                LaunchedEffect(Unit) {

                    navController.navigate(
                        Routes.Login.route
                    ) {
                        popUpTo(Routes.HomeAluno.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }

            } else {

                val unidadeCurricularFactory =
                    remember(
                        container.unidadeCurricularRepository
                    ) {
                        UnidadeCurricularViewModelFactory(
                            repository =
                                container.unidadeCurricularRepository
                        )
                    }

                val unidadeCurricularViewModel:
                    UnidadeCurricularViewModel =
                    viewModel(
                        factory = unidadeCurricularFactory
                    )

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    UnidadeCurricularScreen(
                        modifier =
                            Modifier.padding(innerPadding),
                        viewModel =
                            unidadeCurricularViewModel
                    )
                }
            }
        }
    }
}