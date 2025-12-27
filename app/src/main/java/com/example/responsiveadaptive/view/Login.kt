package com.example.responsiveadaptive.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppBanner(
            widthSizeClass = windowSize.widthSizeClass,
            title = "Gimnasio FitCampus",
            subtitle = "Accede a tu cuenta"
        )

        Spacer(Modifier.height(20.dp))

        val user by vm.loginUsername.observeAsState("")
        val pass by vm.loginPassword.observeAsState("")
        val error by vm.loginError.observeAsState()

        OutlinedTextField(
            value = user,
            onValueChange = vm::setLoginUsername,
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = pass,
            onValueChange = vm::setLoginPassword,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (error != null) {
            Text(error!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (vm.login()) {

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        TextButton(
            onClick = { navController.navigate(Routes.Register.route) }
        ) {
            Text("Crear cuenta")
        }
    }
}
