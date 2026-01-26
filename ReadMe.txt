CAPTURADOR DE CALIFICACIONES
----------------------------
Este programa permite capturar calificaciones de estudiantes y gestionarlas de forma sencilla.


FUNCIONALIDADES
--------------
- Iniciar sesión con autenticación básica.
- Capturar nuevas calificaciones.
- Crear reportes en un archivo CSV y PDF.


REQUISITOS
----------
Antes de poder compilar el programa es necesario tener instalado el JDK 17, o una versión más actual del mismo y Apache Maven. Tambien es necesario haber descargado el archivo "ADA3_U1_T3.zip" y haber extraido su contido ("Manipulador.java" y "Main.java") en una carpeta propia.


ESPECIFICACIONES ADA2 T2
-----------------------
-Crear el algoritmo de encriptamiento (10 minutos). Su implementación fue sencilla por diseño, usamos el cifrado César.
-Crear la funcionalidad de login (40 minutos). Se implementó una autenticación usando el algoritmo de encriptación con la contraseña.

ESPECIFICACIONES ADA2 T3
-----------------------
-Crear la funcionalidad para generar un reporte en pdf (20 minutos). Se utilizó la librería iText para generar el reporte en formato PDF.

PASOS PARA COMPILAR
-------------------
Una vez realizadas esas acciones abra la terminal de la computadora y navegue hasta la carpeta en la que se extrajo el contenido de "ADA2_U1_T1.zip". Una vez ahí ejecute el comando:

    mvn clean install

Este comando compila todos los archivos necesarios para ejecutar el programa.


PASOS PARA EJECUTAR
-------------------
En la ruta donde se encuentra el código fuente ejecute el comando:

    mvn exec:java

Este comando hará que se empiece a ejecutar el programa.

AUTORES
-------
Basulto Cámara José Luis
Osorno Herrera José Alberto
