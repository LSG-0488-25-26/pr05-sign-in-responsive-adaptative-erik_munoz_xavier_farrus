package com.example.responsiveadaptive.view


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.responsiveadaptive.Routes

@Composable
fun RegisterSuccessScreen(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(Modifier.padding(16.dp)) {
            Column(
                Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Registrado correctamente ", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(16.dp))
                Button(onClick = { navController.navigate(Routes.Login.route) }) {
                    Text("Ir a Login")
                }
            }
        }
    }
}
