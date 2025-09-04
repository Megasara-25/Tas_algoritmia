package edu.ucu.aed.ta02;

import java.util.Map;
import java.util.Optional;

public class BuscarISBN {
    public static Optional<Libro> buscar(Map<String, Libro> catalogo, String isbn) {
        return Optional.ofNullable(catalogo.get(isbn));
    }
}
