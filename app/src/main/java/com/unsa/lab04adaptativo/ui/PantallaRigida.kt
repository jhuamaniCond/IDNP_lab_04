package com.unsa.lab04adaptativo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsa.lab04adaptativo.ui.theme.Lab04AdaptativoTheme

/**
 * Punto 2, versión ANTES: la misma pantalla escrita con dimensiones rígidas,
 * tal como suele salir en un primer intento. Se conserva solo para la
 * comparación del informe; no es la pantalla entregada.
 *
 * Problemas que introduce a propósito:
 *  - ancho fijo de 360 dp en la columna raíz: en pantallas más angostas se
 *    corta y en más anchas deja el resto vacío;
 *  - alto fijo de 120 dp para la descripción: el texto se recorta;
 *  - texto medido en dp (convertido a sp con la densidad): ignora la escala
 *    de fuente del sistema;
 *  - botón de 200 x 48 dp: no se estira al ancho de la pantalla ni crece con
 *    la letra;
 *  - separaciones con Spacer de altura fija y sin scroll: nada se reacomoda.
 */
@Composable
fun PantallaRigida(
    apunte: Apunte = apunteEjemplo,
    modifier: Modifier = Modifier,
    onDescargar: () -> Unit = {},
) {
    val densidad = LocalDensity.current
    val letraCuerpoEnDp = with(densidad) { 16.dp.toSp() }  // no respeta fontScale
    val letraTituloEnDp = with(densidad) { 24.dp.toSp() }

    Column(modifier = modifier.width(360.dp).padding(16.dp)) {
        Text(
            text = "Ancho fijo: 360 dp · distribución rígida",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.width(328.dp).height(64.dp)) {
            Text(
                text = apunte.titulo,
                fontSize = letraTituloEnDp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Descripción", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.width(328.dp).height(120.dp)) {
            Text(text = apunte.descripcion, fontSize = letraCuerpoEnDp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.width(328.dp).height(160.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                FilaRigida("Curso", apunte.curso)
                FilaRigida("Autor", apunte.autor)
                FilaRigida("Páginas", apunte.paginas.toString())
                FilaRigida("Valoración", "${apunte.valoracion} / 5")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onDescargar, modifier = Modifier.size(width = 200.dp, height = 48.dp)) {
            Text(text = "Descargar apunte", fontSize = 14.sp)
        }
    }
}

@Composable
private fun FilaRigida(etiqueta: String, valor: String) {
    Row(modifier = Modifier.height(28.dp)) {
        Text(text = etiqueta, modifier = Modifier.width(90.dp), fontSize = 14.sp)
        Text(text = valor, modifier = Modifier.width(200.dp), fontSize = 14.sp)
    }
}

@Preview(name = "Rígida 360 dp", showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun PreviewRigida() {
    Lab04AdaptativoTheme { PantallaRigida() }
}
