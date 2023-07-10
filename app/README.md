# Nombre del Proyecto

Descripción breve del proyecto.

## Requisitos

- Android API 21 o superior
- Lenguaje de programación: Kotlin

## Configuración del proyecto

1. Clona el repositorio en tu máquina local: 

2. Abre el proyecto en Android Studio.

3. Sincroniza las dependencias del proyecto.

## Descripción general

Este proyecto consiste en una aplicación móvil para dispositivos Android que consume un servicio de API Rest proporcionado por The Movie Database (TMDB) para obtener información sobre películas. La aplicación utiliza el lenguaje de programación Kotlin y hace uso de las bibliotecas de compatibilidad de Android.

## Características

1. Consumo de API Rest de TMDB:
- Se consume el servicio API Rest de TMDB para obtener datos sobre películas.
- Se requiere una cuenta gratuita en TMDB para acceder al servicio.

2. Persistencia local de datos:
- Los datos obtenidos del servicio de TMDB se almacenan localmente para su acceso offline.
- La aplicación muestra los datos almacenados localmente cuando el dispositivo no tiene conexión a Internet.

3. Actividad principal y fragmentos:
- La aplicación cuenta con una actividad principal y utiliza fragmentos para mostrar las diferentes funcionalidades.

4. Bottom Navigation:
- Al ingresar a la aplicación, se muestra un Bottom Navigation con 5 opciones.

- Pantalla 1: Pantalla de perfil.
    - Muestra información del usuario más popular.
    - Permite visualizar reseñas hechas por el usuario e imágenes.

- Pantalla 2: Lista de películas.
    - Carga la lista de todas las películas.
    - Muestra las películas más populares, las más calificadas y las mejores recomendaciones.

- Pantalla 3: Ubicaciones en un mapa.
    - Consumo de datos desde la consola de Firebase (Cloud Firestore).
    - Muestra las ubicaciones en un mapa.
    - Muestra la fecha de almacenamiento de cada ubicación.

- Pantalla 4: Captura y carga de imágenes.
    - Permite capturar o seleccionar imágenes de la galería del dispositivo.
    - Sube las imágenes a Firebase Storage.

5. Actualización de ubicación y notificación:
- Agrega la ubicación del dispositivo a una consola de Firebase (Cloud Firestore) cada 5 minutos.
- Notifica al usuario mediante NotificationCompat.

## Contacto

Si tienes alguna pregunta, puedes contactar a Nery Heriberto Bustos a través de nery.h.bustos@gmail.com.