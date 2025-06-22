package dev.luischang.lab03countryapp.presentation.auth

import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import dev.luischang.lab03countryapp.data.remote.firebase.FirebaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController){
    var fullName by remember { mutableStateOf("") }
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var confirmPassword by remember {mutableStateOf("")}

    var acceptTerms by remember { mutableStateOf(false) }
    var showTerms by remember { mutableStateOf(false) }

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

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = acceptTerms, onCheckedChange = { acceptTerms = it })
            Button(onClick = { showTerms = true }) {
                Text("Acepto los términos y condiciones")
            }

        }

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
        //Dialog Terms
        if(showTerms){
            AlertDialog(
                onDismissRequest = { showTerms = false },
                confirmButton = {
                    TextButton(onClick = { showTerms = false }) {
                        Text("Cerrar")
                    }
                },
                title = { Text(text = "Términos y Condiciones") },
                text = {
                    //WebView
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                loadUrl("https://www.privacypolicies.com/live/7541f0b1-9512-4d08-b3db-1746a3ad0303")
                            }
                        },
                        modifier = Modifier.height(300.dp)
                    )
                }
            )
        }
    }
}