package com.example.evaluacion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "pantalla1"
    ) {
        composable("pantalla1") { Pantalla1(navController) }
        composable("honorario") { Pantalla2(navController) }
        composable("regular") { Pantalla3(navController) }
    }
}


@Composable
fun Pantalla1(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cálculo de Pagos")
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { navController.navigate("honorario") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ingresa si eres de Honorarios")
        }
        Button(onClick = { navController.navigate("regular") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ingresa si eres Regular")
        }
    }
}


@Composable
fun Pantalla2(navController: NavHostController) {
    var sueldoBruto by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Honorarios")


        TextField(
            value = sueldoBruto,
            onValueChange = { sueldoBruto = it },
            label = { Text("Ingrese Sueldo Bruto") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val sueldo = sueldoBruto.toDoubleOrNull() ?: 0.0
            resultado = "El Sueldo líquido es: ${calcularLiquido(sueldo, currentRoute)}"
        }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = resultado)


        Button(onClick = { navController.navigate("pantalla1") }) {
            Text("Volver a la pantalla principal")
        }
    }
}

fun calcularLiquido(sueldoBruto: Double, route: String?): Double {
    return if (route == "honorario") {
        sueldoBruto - ((sueldoBruto * 13) / 100)
    } else {
        sueldoBruto - ((sueldoBruto * 20) / 100)
    }
}


@Composable
fun Pantalla3(navController: NavHostController) {
    var sueldoBruto by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Regular")


        TextField(
            value = sueldoBruto,
            onValueChange = { sueldoBruto = it },
            label = { Text("Ingrese Sueldo Bruto") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val sueldo = sueldoBruto.toDoubleOrNull() ?: 0.0
            resultado = "El Sueldo líquido es: ${calcularLiquido(sueldo, currentRoute)}"
        }) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = resultado)


        Button(onClick = { navController.navigate("pantalla1") }) {
            Text("Volver a la pantalla principal")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}