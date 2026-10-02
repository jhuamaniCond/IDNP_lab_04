package com.unsa.lab04adaptativo.ui

import android.content.res.Configuration
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
 * Punto 1 y 3: Pantalla adaptativa que soporta cambios de ancho disponible
 * y se adapta automáticamente a Modo Claro / Modo Oscuro mediante [MaterialTheme].
 *
 * Todos los textos e íconos consumen [MaterialTheme.colorScheme] y [MaterialTheme.typography]
 * eliminando valores de color o tamaño fijos (hardcoded).
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
            // Columna izquierda: Descriocion (con Scroll)
            Column(
                modifier = Modifier
                    .weight(3f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(Espaciado.m),
            ) {
                BloqueContenido(apunte)
            }
            // Columna derecha: Ficha y Boton (AJUSTE: Se agrega verticalScroll para Landscape)
            Column(
                modifier = Modifier
                    .weight(2f)
                    .verticalScroll(rememberScrollState()), // <--- Ajuste: verticalScroll
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
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = apunte.descripcion,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            FilaDato("Autor", apunte.autor)
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            FilaDato("Páginas", apunte.paginas.toString())
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
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
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = valor,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
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
@Preview(
    name = "Modo Claro - Compacto (360 dp)",
    showBackground = true,
    widthDp = 360,
    heightDp = 780,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun PreviewModoClaroCompacto() {
    Lab04AdaptativoTheme(darkTheme = false) {
        PantallaAdaptativa()
    }
}

@Preview(
    name = "Modo Oscuro - Compacto (360 dp)",
    showBackground = true,
    widthDp = 360,
    heightDp = 780,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewModoOscuroCompacto() {
    Lab04AdaptativoTheme(darkTheme = true) {
        PantallaAdaptativa()
    }
}

@Preview(
    name = "Modo Oscuro - Amplio (840 dp)",
    showBackground = true,
    widthDp = 840,
    heightDp = 600,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewModoOscuroAmplio() {
    Lab04AdaptativoTheme(darkTheme = true) {
        PantallaAdaptativa()
    }
}

// PREVIEW PARA PUTNO 4: Prueba de Fuente Aumentada (fontscale = 1.5x)
@Preview(
    name = "Punto 4 - Fuente Aumentada (1.5x)",
    showBackground = true,
    widthDp = 360,
    heightDp = 780,
    fontScale = 1.5f
)
@Composable
private fun PreviewFuenteAumentada() {
    Lab04AdaptativoTheme {
        PantallaAdaptativa()
    }
}

// PREVIEW PARA PUNTO 5: Pantalla Amplia / Tableta (1280 dp)
@Preview(
    name = "Punto 5 - Tableta / Pantalla Amplia (1280 dp)",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
private fun PreviewTabletAmplia() {
    Lab04AdaptativoTheme {
        PantallaAdaptativa()
    }
}

// PREVIEW PARA PUNTO 6: Orientación Landscape / Apaisado (800x360 dp)
@Preview(
    name = "Punto 6 - Orientacion Landscape (800x360 dp)",
    showBackground = true,
    widthDp = 800,
    heightDp = 360
)
@Composable
private fun PreviewLandscape() {
    Lab04AdaptativoTheme {
        PantallaAdaptativa()
    }
}