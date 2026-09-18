package com.unsa.lab04adaptativo.ui

/**
 * Datos de ejemplo de la pantalla. Corresponden a la pantalla "Detalle de
 * apunte" de CampusDocs (proyecto final del curso), identificada como
 * pantalla sensible al tamaño: en teléfono el texto largo obliga a apilar
 * todo, en tableta sobra ancho y conviene poner la ficha al lado.
 */
data class Apunte(
    val titulo: String,
    val curso: String,
    val autor: String,
    val paginas: Int,
    val valoracion: Double,
    val descripcion: String,
)

val apunteEjemplo = Apunte(
    titulo = "Cálculo II · Semana 6: Integración por partes y sustitución trigonométrica",
    curso = "Cálculo II",
    autor = "María Fernanda Quispe",
    paginas = 14,
    valoracion = 4.7,
    descripcion = "Apunte de clase con la deducción de la fórmula de integración " +
        "por partes, la regla nemotécnica ILATE para elegir u y dv, y doce " +
        "ejercicios resueltos paso a paso. Incluye además la tabla de " +
        "sustituciones trigonométricas para raíces de la forma a² − x², " +
        "a² + x² y x² − a², con un ejemplo completo de cada caso y las " +
        "identidades que se usan para volver a la variable original. Al " +
        "final hay una lista de errores frecuentes detectados en la práctica " +
        "calificada anterior y cómo evitarlos.",
)
