package edu.ucu.aed.tda.lista;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TDALista<T extends Comparable<T>> {
    boolean insertar(T data);

    T buscar(Comparable<T> identificador);

    T eliminar(Comparable<T> identificador);

    String imprimir();

    String imprimir(String delimitador);

    int cantElementos();

    boolean esVacia();

}
