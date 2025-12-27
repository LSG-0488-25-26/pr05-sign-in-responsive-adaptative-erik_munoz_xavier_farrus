package com.example.responsiveadaptive.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.responsiveadaptive.Routes
import com.example.responsiveadaptive.viewmodel.AuthViewModel
import com.example.responsiveadaptive.view.AppBanner

@Composable
fun RegisterScreen(
    navController: NavController,
    vm: AuthViewModel,
    windowSize: WindowSizeClass
) {
    val widthSizeClass = windowSize.widthSizeClass

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact ->
            RegisterCompact(navController, vm, widthSizeClass)

        WindowWidthSizeClass.Medium ->
            RegisterMedium(navController, vm, widthSizeClass)

        else ->
            RegisterExpanded(navController, vm, widthSizeClass)
    }
}


@Composable
private fun RegisterCompact(
    navController: NavController,
    vm: AuthViewModel,
    widthSizeClass: WindowWidthSizeClass
) {
    val config = LocalConfiguration.current
    val isLandscape = config.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(if (isLandscape) 10.dp else 16.dp)
    ) {
        AppBanner(
            widthSizeClass = widthSizeClass,
            title = "Gimnasio FitCampus",
            subtitle = "Tu progreso, cada día"
        )

        Spacer(Modifier.height(12.dp))

        RegisterForm(
            navController = navController,
            vm = vm,
            formWidthFraction = if (isLandscape) 0.95f else 1f,
            showSideHelp = false
        )
    }
}


@Composable
private fun RegisterMedium(
    navController: NavController,
    vm: AuthViewModel,
    widthSizeClass: WindowWidthSizeClass
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            AppBanner(
                widthSizeClass = widthSizeClass,
                title = "Gimnasio FitCampus",
                subtitle = "Tu progreso, cada día"
            )

            Spacer(Modifier.height(12.dp))

            RegisterForm(
                navController = navController,
                vm = vm,
                formWidthFraction = 0.9f,
                showSideHelp = false
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
        ) {
            Card {
                Column(Modifier.padding(16.dp)) {
                    Text("Consejos", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Text("• Email con @")
                    Text("• Teléfono solo números")
                    Text("• Password mínimo 6")
                    Text("• Usuario único")
                }
            }
        }
    }
}


@Composable
private fun RegisterExpanded(
    navController: NavController,
    vm: AuthViewModel,
    widthSizeClass: WindowWidthSizeClass
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
        ) {
            AppBanner(
                widthSizeClass = widthSizeClass,
                title = "Gimnasio FitCampus",
                subtitle = "Tu progreso, cada día"
            )

            Spacer(Modifier.height(12.dp))

            Card {
                Column(Modifier.padding(16.dp)) {
                    Text("Bienvenido/a", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Text("Regístrate para acceder al servicio.")
                }
            }
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            RegisterForm(
                navController = navController,
                vm = vm,
                formWidthFraction = 0.85f,
                showSideHelp = true
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
        ) {
            Card {
                Column(Modifier.padding(16.dp)) {
                    Text("¿Ya tienes cuenta?", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(10.dp))
                    OutlinedButton(
                        onClick = { navController.navigate(Routes.Login.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ir a Login")
                    }
                }
            }
        }
    }
}


@Composable
private fun RegisterForm(
    navController: NavController,
    vm: AuthViewModel,
    formWidthFraction: Float,
    showSideHelp: Boolean
) {
    val fullName by vm.fullName.observeAsState("")
    val birthDate by vm.birthDate.observeAsState("")
    val email by vm.email.observeAsState("")
    val phone by vm.phone.observeAsState("")
    val username by vm.username.observeAsState("")
    val password by vm.password.observeAsState("")
    val confirmPassword by vm.confirmPassword.observeAsState("")
    val termsAccepted by vm.termsAccepted.observeAsState(false)

    val fullNameError by vm.fullNameError.observeAsState(null)
    val birthDateError by vm.birthDateError.observeAsState(null)
    val emailError by vm.emailError.observeAsState(null)
    val phoneError by vm.phoneError.observeAsState(null)
    val usernameError by vm.usernameError.observeAsState(null)
    val passwordError by vm.passwordError.observeAsState(null)
    val confirmPasswordError by vm.confirmPasswordError.observeAsState(null)
    val termsError by vm.termsError.observeAsState(null)

    Column(modifier = Modifier.fillMaxWidth(formWidthFraction)) {

        Text("Registro", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = vm::setFullName,
            label = { Text("Nombre completo") },
            isError = fullNameError != null,
            modifier = Modifier.fillMaxWidth()
        )
        FieldError(fullNameError)

        OutlinedTextField(
            value = birthDate,
            onValueChange = vm::setBirthDate,
            label = { Text("Fecha nacimiento (dd/mm/yyyy)") },
            isError = birthDateError != null,
            modifier = Modifier.fillMaxWidth()
        )
        FieldError(birthDateError)

        OutlinedTextField(
            value = email,
            onValueChange = vm::setEmail,
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError != null,
            modifier = Modifier.fillMaxWidth()
        )
        FieldError(emailError)

        OutlinedTextField(
            value = phone,
            onValueChange = vm::setPhone,
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = phoneError != null,
            modifier = Modifier.fillMaxWidth()
        )
        FieldError(phoneError)

        OutlinedTextField(
            value = username,
            onValueChange = vm::setUsername,
            label = { Text("Nombre de usuario") },
            isError = usernameError != null,
            modifier = Modifier.fillMaxWidth()
        )
        FieldError(usernameError)

        OutlinedTextField(
            value = password,
            onValueChange = vm::setPassword,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            modifier = Modifier.fillMaxWidth()
        )
        FieldError(passwordError)

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = vm::setConfirmPassword,
            label = { Text("Confirmar password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = confirmPasswordError != null,
            modifier = Modifier.fillMaxWidth()
        )
        FieldError(confirmPasswordError)

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = vm::setTermsAccepted
            )
            Spacer(Modifier.width(8.dp))
            Text("Acepto términos y condiciones")
        }
        FieldError(termsError)

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                val ok = vm.register()
                if (ok) navController.navigate(Routes.RegisterSuccess.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        if (showSideHelp) {
            Spacer(Modifier.height(12.dp))
            AssistChip(
                onClick = {},
                label = { Text("Tip: usuario debe ser único") }
            )
        }

        TextButton(
            onClick = { navController.navigate(Routes.Login.route) }
        ) {
            Text("Ir al Login")
        }
    }
}

@Composable
private fun FieldError(msg: String?) {
    if (msg != null) {
        Text(
            text = msg,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )
    } else {
        Spacer(Modifier.height(8.dp))
    }
}
