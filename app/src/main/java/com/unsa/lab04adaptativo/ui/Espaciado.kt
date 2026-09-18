package com.unsa.lab04adaptativo.ui

import androidx.compose.ui.unit.dp

/**
 * Escala de espaciado coherente (múltiplos de 4 dp). Es el único sitio del
 * módulo donde se escriben valores en dp para márgenes y separaciones: las
 * pantallas los referencian por nombre en lugar de repetir números.
 */
object Espaciado {
    val xs = 4.dp
    val s = 8.dp
    val m = 16.dp
    val l = 24.dp
}

/**
 * Ancho a partir del cual hay espacio suficiente para una distribución
 * amplia (dos columnas). Coincide con el límite entre las clases de tamaño
 * de ventana Compact y Medium de Material 3 (600 dp).
 */
val ANCHO_AMPLIO = 600.dp
