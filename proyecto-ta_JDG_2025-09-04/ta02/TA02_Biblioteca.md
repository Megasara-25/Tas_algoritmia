# TA02 — Biblioteca 

## Descripción
Catálogo de libros con adquisiciones, préstamos con tope, devoluciones, retiro, consulta y listado por **título**. Se integran archivos y se reportan totales.

## Precondiciones
- ISBN único, cantidades no negativas.
- Archivos bien formados.

## Postcondiciones
- Invariantes: `0 ≤ disp ≤ total`.
- Totales: monetario agregado y variación neta de préstamos.

## Seudocódigo y complejidad
Ver `Biblioteca.java` (O(1) promedio por operación de mapa; ordenar por título O(m log m)).
