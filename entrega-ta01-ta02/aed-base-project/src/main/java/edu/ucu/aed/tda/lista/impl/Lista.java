
package edu.ucu.aed.tda.lista.impl;

import edu.ucu.aed.tda.lista.TDALista;
import edu.ucu.aed.tda.lista.TDANodo;

public class Lista<T extends Comparable<T>> implements TDALista<T> {
    private TDANodo<T> primero;
    private int tamaño;

    public Lista() {
        this.primero = null;
        this.tamaño = 0;
    }

    @Override
    public boolean insertar(T dato) {
        if (dato == null) return false;
        TDANodo<T> nuevo = new Nodo<>(dato, null);

        // Insertar ordenado (ascendente) según compareTo
        if (primero == null) {
            primero = nuevo;
            tamaño++;
            return true;
        }
        // Si debe ir al inicio
        if (dato.compareTo(primero.getDato()) <= 0) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
            tamaño++;
            return true;
        }
        // Buscar posición
        TDANodo<T> actual = primero;
        while (actual.getSiguiente() != null && actual.getSiguiente().getDato().compareTo(dato) < 0) {
            actual = actual.getSiguiente();
        }
        // Evitar duplicados si el TDA lo exige (no especificado):
        // Si se quisiera evitar duplicados exactos por equals/compareTo==0, se podría retornar false aquí.
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        tamaño++;
        return true;
    }

    @Override
    public T buscar(Comparable<T> identificador) {
        if (identificador == null) return null;
        TDANodo<T> actual = primero;
        while (actual != null) {
            int cmp = identificador.compareTo(actual.getDato());
            if (cmp == 0) return actual.getDato();
            // Si la lista está ordenada ascendente, y el identificador ya es "menor"
            // que el dato actual, podemos cortar; pero como no sabemos la regla de
            // comparación del identificador vs. orden de lista, seguimos lineal.
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public T eliminar(Comparable<T> identificador) {
        if (identificador == null || primero == null) return null;
        // Si es el primero
        if (identificador.compareTo(primero.getDato()) == 0) {
            T dato = primero.getDato();
            primero = primero.getSiguiente();
            tamaño--;
            return dato;
        }
        TDANodo<T> actual = primero;
        while (actual.getSiguiente() != null) {
            if (identificador.compareTo(actual.getSiguiente().getDato()) == 0) {
                T dato = actual.getSiguiente().getDato();
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return dato;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public String imprimir() {
        return imprimir(" ");
    }

    @Override
    public String imprimir(String delimitador) {
        StringBuilder sb = new StringBuilder();
        TDANodo<T> actual = primero;
        boolean first = true;
        while (actual != null) {
            if (!first) sb.append(delimitador);
            sb.append(actual.getDato());
            first = false;
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    @Override
    public int cantElementos() {
        return tamaño;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }
}
