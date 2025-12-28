package com.example.responsiveadaptive.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.responsiveadaptive.Routes
import com.example.responsiveadaptive.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    vm: AuthViewModel,
    windowSize: WindowSizeClass
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact ->
            LoginCompact(navController, vm, windowSize)

        WindowWidthSizeClass.Medium ->
            LoginMedium(navController, vm, windowSize)

        else ->
            LoginExpanded(navController, vm, windowSize)
    }
}
@Composable
private fun LoginCompact(
    navController: NavController,
    vm: AuthViewModel,
    windowSize: WindowSizeClass
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AppBanner(
            widthSizeClass = windowSize.widthSizeClass,
            title = "Gimnasio FitCampus",
            subtitle = "Accede a tu cuenta"
        )

        Spacer(Modifier.height(24.dp))
        LoginForm(navController, vm)
    }
}

@Composable
private fun LoginMedium(
    navController: NavController,
    vm: AuthViewModel,
    windowSize: WindowSizeClass
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        AppBanner(
            widthSizeClass = windowSize.widthSizeClass,
            title = "Gimnasio FitCampus",
            subtitle = "Accede a tu cuenta"
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.weight(1f)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Bienvenido/a", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Text("Loguéate para acceder al servicio.")
                }
            }

            Spacer(Modifier.width(16.dp))

            Card(
                modifier = Modifier.weight(1f)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "¿Aún no tienes cuenta?",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedButton(
                        onClick = { navController.navigate(Routes.Register.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Crear cuenta")
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier.widthIn(max = 420.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(Modifier.padding(24.dp)) {
                    LoginForm(navController, vm)
                }
            }
        }
    }
}

@Composable
private fun LoginExpanded(
    navController: NavController,
    vm: AuthViewModel,
    windowSize: WindowSizeClass
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp) // similar al medium
    ) {

        AppBanner(
            widthSizeClass = windowSize.widthSizeClass,
            title = "Gimnasio FitCampus",
            subtitle = "Accede a tu cuenta"
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.weight(1f),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Bienvenido/a", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Text("Loguéate para acceder al servicio.")
                }
            }

            Spacer(Modifier.width(16.dp))

            Card(
                modifier = Modifier.weight(1f),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "¿Aún no tienes cuenta?",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedButton(
                        onClick = { navController.navigate(Routes.Register.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Crear cuenta")
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier.widthIn(max = 420.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(Modifier.padding(24.dp)) {
                    LoginForm(navController, vm)
                }
            }
        }
    }
}


@Composable
private fun LoginForm(
    navController: NavController,
    vm: AuthViewModel
) {
    val user by vm.loginUsername.observeAsState("")
    val pass by vm.loginPassword.observeAsState("")
    val error by vm.loginError.observeAsState()

    Text(
        text = "Login",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.fillMaxWidth(),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )

    Spacer(Modifier.height(16.dp))

    OutlinedTextField(
        value = user,
        onValueChange = vm::setLoginUsername,
        label = { Text("Usuario") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = pass,
        onValueChange = vm::setLoginPassword,
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    if (error != null) {
        Text(
            text = error!!,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(top = 8.dp)
        )
    }

    Spacer(Modifier.height(24.dp))

    Button(
        onClick = { vm.login() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Entrar")
    }
}
