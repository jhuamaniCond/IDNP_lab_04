package com.unsa.lab04adaptativo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.unsa.lab04adaptativo.ui.PantallaAdaptativa
import com.unsa.lab04adaptativo.ui.PantallaRigida
import com.unsa.lab04adaptativo.ui.theme.Lab04AdaptativoTheme

/**
 * Laboratorio 04 - Diseño adaptativo en pantallas móviles (2026-B).
 *
 * Integrante 1: puntos 1 y 2 de la actividad práctica.
 *
 * Por defecto se muestra [PantallaAdaptativa]. Para comparar con la versión
 * "antes" (dimensiones rígidas) se lanza la actividad con el extra `modo`:
 *
 *   adb shell am start -n com.unsa.lab04adaptativo/.MainActivity --es modo rigida
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val modoRigido = intent?.getStringExtra("modo") == "rigida"
        Log.d("LAB04", "Inicio Lab 04 (2026) - modo=${if (modoRigido) "rigida" else "adaptativa"}")
        setContent {
            Lab04AdaptativoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (modoRigido) {
                        PantallaRigida(modifier = Modifier.padding(innerPadding))
                    } else {
                        PantallaAdaptativa(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
