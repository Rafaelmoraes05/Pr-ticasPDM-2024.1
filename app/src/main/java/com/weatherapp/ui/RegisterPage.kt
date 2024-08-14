package com.weatherapp.ui

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.weatherapp.LoginActivity
import com.weatherapp.MainActivity
import com.weatherapp.RegisterActivity
import com.weatherapp.showToastAndFinish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(activity: ComponentActivity) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nome de Usuário") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                Firebase.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity!!) { task ->
                        if (task.isSuccessful) {
                            showToastAndFinish(activity)
                        } else {
                            Toast.makeText(activity, "Registro FALHOU: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            },
            enabled = username.isNotEmpty() && email.isNotEmpty() &&
                    password.isNotEmpty() && confirmPassword.isNotEmpty() &&
                    password == confirmPassword
        ) {
            Text("Registrar")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { /* Limpar campos */ }) {
            Text("Limpar")
        }
    }
}