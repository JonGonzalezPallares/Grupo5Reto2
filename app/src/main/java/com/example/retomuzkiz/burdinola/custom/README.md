# Custom 🛃

Este directorio contiene la vista personalizada utilizada para mostrar el rompecabezas, `GridView`.

## Grid View
La vista toma una matriz anidada de caracteres y la muestra en la pantalla. Cada personaje tiene su propio "mosaico" en la cuadrícula. Para detectar qué mosaicos tocó el usuario en la pantalla, ampliamos la interfaz `View.OnTouchListener`.

El método `onTouch` se sobrescribe de manera que cuando el usuario toca la pantalla por primera vez, comienza a registrar los caracteres que el usuario está deslizando. Una vez que el dedo del usuario se levanta de la pantalla, la cadena seleccionada se pasa al método `wordFound` para verificar si es una de las palabras que forma parte del rompecabezas. Mientras el usuario selecciona las celdas, se resaltan en color amarillo.

Si la cadena pasada al método `wordFound` es parte del rompecabezas y aún no se ha encontrado, la puntuación se incrementa en uno y las fichas se resaltan en color azul. Si la cadena que se pasa al método corresponde a una palabra ya encontrada o no forma parte del rompecabezas, la puntuación no cambia y no se resalta ninguna ficha. En todos los casos se devuelve un pequeño 🍞.

Aquí se utilizan datos en vivo de `score` para realizar un seguimiento de la puntuación, y para que se pueda pasar de nuevo a `GameViewModel`.

## Util

`getRandomIntRange` genera un numero entre el rango min y max para generar un character random