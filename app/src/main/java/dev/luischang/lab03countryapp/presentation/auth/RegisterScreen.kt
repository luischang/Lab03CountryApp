package dev.luischang.lab03countryapp.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.luischang.lab03countryapp.data.remote.firebase.FirebaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController){
    var fullName by remember { mutableStateOf("") }
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var confirmPassword by remember {mutableStateOf("")}

    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp))
    {
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Registro", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = {
            // Lógica de registro
            if(password == confirmPassword && fullName.isNotBlank())
            {
                CoroutineScope(Dispatchers.Main).launch {
                    var result = FirebaseAuthManager.registerUser(fullName, email, password)
                    if(result.isSuccess)
                    {
                        navController.navigate("login")
                    }
                    else {
                        val error = result.exceptionOrNull()?.message ?: "Error desconocido"
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
                //navController.navigate("login")
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Registrarse")
        }
    }
}