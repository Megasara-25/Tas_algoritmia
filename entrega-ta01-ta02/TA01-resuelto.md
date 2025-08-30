
# Trabajo de Aplicación 1 — Resolución

**Ejercicio 0**

**Pregunta 1.** Opción correcta: **3) Agregar un nodo al principio de la lista.**  
El código crea un nuevo nodo que apunta a `cabeza` y luego actualiza `cabeza` al nuevo nodo.

**Pregunta 2.** Opción correcta: **4) Aunque actual no sea null al inicio, ejecuta indefinidamente.**  
En una lista **circular** el campo `siguiente` **nunca** es `null`, por lo que el `while` no termina.

**Pregunta 3.** Opción correcta: **4) Inserta el nodo temporal a continuación del nodo actual.**  
Las asignaciones dejan `temporal` entre `actual` y `actual.siguiente` original.

**Pregunta 4.** Opción correcta: **1) Elimina de la lista al nodo temporal.**  
Se re-enlaza `actual.siguiente` para “saltar” al `temporal`, quitándolo de la cadena.

---

**Ejercicio 1 — Análisis de implementaciones (array vs. lista enlazada)**

- **Memoria**  
  - *Array:* reserva un bloque contiguo; si se sobredimensiona, hay desperdicio.  
  - *Lista simple:* cada nodo guarda puntero y dato; sin capacidad fija, pero con overhead por nodo.
- **Cantidad de libros soportada**  
  - *Array:* limitada por el tamaño elegido; crecer implica copiar a un array más grande.  
  - *Lista:* dinámica; sólo limitada por memoria disponible.
- **Eficiencia por operación**  
  - *Alta/Inserción ordenada:*  
    - Array: O(n) (corrimientos).  
    - Lista: O(n) para ubicar posición; O(1) para enlazar.  
  - *Búsqueda por código/título (sin índice):* O(n) en ambos.  
  - *Eliminar:*  
    - Array: O(n) por corrimientos.  
    - Lista: O(n) para ubicar y O(1) para desenlazar.  
  - *Listar ordenado por título:* si se mantiene orden al insertar, el recorrido es O(n) en ambos (ya ordenados).

---

**Ejercicio 2 — Alta de nuevo libro en lista simple**

**Descripción (alto nivel):**  
Insertar el libro en el catálogo manteniendo el **orden por título**; si el código ya existe, **no duplicar**: actualizar precio/título y **sumar stock**.

**Precondiciones:**  
- El TDA Lista está inicializado.  
- Se recibe un objeto `Libro` válido (código no vacío, título válido, precio ≥ 0, stock ≥ 0).

**Postcondiciones:**  
- Si el libro **no existía**, queda insertado en la posición correcta por título.  
- Si el libro **existía** (mismo código), se actualiza título/precio y se ajusta el stock según la operación.

**Seudocódigo detallado:**

```
func altaLibro(codigo, titulo, precioReposicion, cantidad):
    libro = buscarPorCodigo(codigo)
    si libro == null entonces
        nuevo = Libro(codigo, titulo, precioReposicion, 0)
        lista.insertar(nuevo)          // inserción ordenada por título (compareTo)
        libro = nuevo
    si precioReposicion >= 0 entonces
        libro.precioReposicion = precioReposicion
    si titulo no vacío entonces
        libro.titulo = titulo
    libro.stock = libro.stock + max(0, cantidad)
    devolver true
```

> Nota: `lista.insertar` mantiene el orden por `Libro.compareTo` (título).
