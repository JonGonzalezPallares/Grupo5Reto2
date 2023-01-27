# Word Placement 💬

## Word
Data class que contiene la palabra y si se ha encontrado.

## WordSearch
Contiene todos los métodos necesarios para hacer el rompecabezas.
- `placeWordList` toma una lista de palabras y una lista de tipos de ubicación, las mezcla y las pasa a `placeWord`
- `placeWord` elige coordenadas aleatorias y pasa eso, así como la palabra en `findEmptySection`. Si devuelve verdadero, entonces coloca la palabra que comienza en esas coordenadas y le pasa el tipo de ubicación.
- `findEmptySection` comprueba si la palabra puede caber dadas las coordenadas de inicio y el tipo de ubicación
- `fillSlots` llena los espacios vacíos en la cuadrícula después de colocar las  palabras
- `movement` devuelve el movimiento asociado a cada tipo de ubicación

## PlacementType
Enum class contiene todos los diferentes tipos de ubicación posibles.