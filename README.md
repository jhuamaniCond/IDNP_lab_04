# Lab 04 - Diseño adaptativo en pantallas móviles (2026-B)

Parte del **Integrante 1** (puntos 1 y 2 de la guía): pantalla "Detalle de
apunte" de CampusDocs con título, bloque de contenido y acción principal, que
pasa de una columna a dos según el ancho disponible (`BoxWithConstraints`,
umbral 600 dp), sin dimensiones rígidas.

- `app/src/main/java/com/unsa/lab04adaptativo/ui/PantallaAdaptativa.kt`: pantalla entregada.
- `.../ui/PantallaRigida.kt`: versión "antes" con tamaños fijos, solo para la comparación del informe.
- `.../ui/Espaciado.kt`: escala de espaciado y `ANCHO_AMPLIO`.

## Compilar e instalar

```
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.unsa.lab04adaptativo/.MainActivity                   # adaptativa
adb shell am start -n com.unsa.lab04adaptativo/.MainActivity --es modo rigida  # antes
```

## Simular otros anchos en el mismo emulador

```
adb shell wm size 1600x2560 && adb shell wm density 320   # 800 x 1280 dp (tableta)
adb shell wm size 720x1280  && adb shell wm density 360   # 320 x 569 dp (teléfono pequeño)
adb shell wm size reset     && adb shell wm density reset
```

Las capturas están en `../img` y el informe se genera con `python ../docs/generar_informe.py`.
