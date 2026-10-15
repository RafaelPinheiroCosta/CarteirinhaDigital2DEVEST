package com.rafaelcosta.carteirinhadigital2devest.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rafaelcosta.carteirinhadigital2devest.app.di.AppContainer
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.aluno.alunoNavGraph
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.auth.authNavGraph
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.professor.professorNavGraph
import com.rafaelcosta.carteirinhadigital2devest.app.session.SessionViewModel
import com.rafaelcosta.carteirinhadigital2devest.app.session.SessionViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.PerfilAcesso

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

    val sessionViewModel: SessionViewModel = viewModel(
        factory = sessionFactory
    )

    fun graphFor(perfil: PerfilAcesso): String {
        return when (perfil) {
            PerfilAcesso.ALUNO -> GraphRoutes.ALUNO
            PerfilAcesso.PROFESSOR -> GraphRoutes.PROFESSOR
        }
    }

    fun replaceGraph(
        currentGraph: String,
        destinationGraph: String
    ) {

        navController.navigate(destinationGraph) {
            popUpTo(currentGraph) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = GraphRoutes.AUTH
    ) {
        authNavGraph(
            loginRepository = container.loginRepository,
            onLoginSucesso = { usuario ->
                sessionViewModel.setUsuarioLogado(usuario)
                replaceGraph(
                    currentGraph = GraphRoutes.AUTH,
                    destinationGraph = graphFor(usuario.perfil)
                )
            }
        )

        alunoNavGraph(
            navController = navController,
            usuarioLogadoFlow = sessionViewModel.usuarioLogado,
            unidadeCurricularRepository = container.unidadeCurricularRepository,
            darkTheme = darkTheme,
            onDarkThemeChange = onDarkThemeChange,
            onLogout = {
                sessionViewModel.limparSessao()
                replaceGraph(
                    currentGraph = GraphRoutes.ALUNO,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onSessaoAusente = {
                replaceGraph(
                    currentGraph = GraphRoutes.ALUNO,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onPerfilInvalido = { perfil ->
                replaceGraph(
                    currentGraph = GraphRoutes.ALUNO,
                    destinationGraph = graphFor(perfil)
                )
            }
        )

        professorNavGraph(
            usuarioLogadoFlow = sessionViewModel.usuarioLogado,
            darkTheme = darkTheme,
            onDarkThemeChange = onDarkThemeChange,
            onLogout = {
                sessionViewModel.limparSessao()
                replaceGraph(
                    currentGraph = GraphRoutes.PROFESSOR,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onSessaoAusente = {
                replaceGraph(
                    currentGraph = GraphRoutes.PROFESSOR,
                    destinationGraph = GraphRoutes.AUTH
                )
            },
            onPerfilInvalido = { perfil ->
                replaceGraph(
                    currentGraph = GraphRoutes.PROFESSOR,
                    destinationGraph = graphFor(perfil)
                )
            }
        )
    }
}