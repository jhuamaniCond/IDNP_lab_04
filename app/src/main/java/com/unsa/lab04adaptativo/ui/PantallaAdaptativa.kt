package com.unsa.lab04adaptativo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.unsa.lab04adaptativo.ui.theme.Lab04AdaptativoTheme

/**
 * Punto 1: pantalla con título, bloque de contenido y acción principal que
 * cambia de organización según el ancho disponible.
 *
 * - Ancho < 600 dp (teléfono en vertical): una sola columna con scroll.
 * - Ancho >= 600 dp (tableta, teléfono apaisado): dos columnas, el
 *   contenido a la izquierda y la ficha con la acción a la derecha.
 *
 * La decisión se toma con [BoxWithConstraints], que entrega el espacio que
 * realmente recibe el composable, no el tamaño nominal del dispositivo.
 */
@Composable
fun PantallaAdaptativa(
    apunte: Apunte = apunteEjemplo,
    modifier: Modifier = Modifier,
    onDescargar: () -> Unit = {},
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val amplio = maxWidth >= ANCHO_AMPLIO
        val etiquetaAncho = "Ancho disponible: ${maxWidth.value.toInt()} dp · " +
            if (amplio) "distribución amplia (dos columnas)" else "distribución vertical"

        if (amplio) {
            DistribucionAmplia(apunte, etiquetaAncho, onDescargar)
        } else {
            DistribucionVertical(apunte, etiquetaAncho, onDescargar)
        }
    }
}

// ---------------------------------------------------------------- vertical
@Composable
private fun DistribucionVertical(
    apunte: Apunte,
    etiquetaAncho: String,
    onDescargar: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Espaciado.m),
        verticalArrangement = Arrangement.spacedBy(Espaciado.m),
    ) {
        IndicadorAncho(etiquetaAncho)
        Titulo(apunte)
        BloqueContenido(apunte)
        FichaApunte(apunte)
        AccionPrincipal(onDescargar, modifier = Modifier.fillMaxWidth())
    }
}

// ------------------------------------------------------------------ amplia
@Composable
private fun DistribucionAmplia(
    apunte: Apunte,
    etiquetaAncho: String,
    onDescargar: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Espaciado.l),
        verticalArrangement = Arrangement.spacedBy(Espaciado.m),
    ) {
        IndicadorAncho(etiquetaAncho)
        Titulo(apunte)
        Row(horizontalArrangement = Arrangement.spacedBy(Espaciado.l)) {
            // El contenido se lleva 3/5 del ancho y la ficha 2/5: proporciones,
            // no anchos fijos, para que sirva igual a 600 dp que a 1280 dp.
            Column(
                modifier = Modifier
                    .weight(3f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(Espaciado.m),
            ) {
                BloqueContenido(apunte)
            }
            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.spacedBy(Espaciado.m),
            ) {
                FichaApunte(apunte)
                AccionPrincipal(onDescargar, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

// ------------------------------------------------------------- componentes
@Composable
private fun IndicadorAncho(texto: String) {
    Text(
        text = texto,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun Titulo(apunte: Apunte) {
    Text(
        text = apunte.titulo,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun BloqueContenido(apunte: Apunte) {
    Column(verticalArrangement = Arrangement.spacedBy(Espaciado.s)) {
        Text(
            text = "Descripción",
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = apunte.descripcion,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun FichaApunte(apunte: Apunte) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(Espaciado.m),
            verticalArrangement = Arrangement.spacedBy(Espaciado.s),
        ) {
            FilaDato("Curso", apunte.curso)
            HorizontalDivider()
            FilaDato("Autor", apunte.autor)
            HorizontalDivider()
            FilaDato("Páginas", apunte.paginas.toString())
            HorizontalDivider()
            FilaDato("Valoración", "${apunte.valoracion} / 5")
        }
    }
}

@Composable
private fun FilaDato(etiqueta: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Espaciado.s),
    ) {
        Text(
            text = etiqueta,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = valor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(2f),
        )
    }
}

@Composable
private fun AccionPrincipal(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(
            text = "Descargar apunte",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(vertical = Espaciado.xs),
        )
    }
}

// ---------------------------------------------------------------- previews
@Preview(name = "Compacto 360 dp", showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun PreviewCompacto() {
    Lab04AdaptativoTheme { PantallaAdaptativa() }
}

@Preview(name = "Amplio 840 dp", showBackground = true, widthDp = 840, heightDp = 600)
@Composable
private fun PreviewAmplio() {
    Lab04AdaptativoTheme { PantallaAdaptativa() }
}

@Preview(name = "Compacto, fuente 1.5x", showBackground = true, widthDp = 360, heightDp = 780, fontScale = 1.5f)
@Composable
private fun PreviewCompactoFuenteGrande() {
    Lab04AdaptativoTheme { PantallaAdaptativa() }
}
