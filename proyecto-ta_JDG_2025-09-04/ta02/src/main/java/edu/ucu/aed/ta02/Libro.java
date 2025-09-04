package edu.ucu.aed.ta02;

import java.util.Objects;

public class Libro {
    private final String isbn;
    private final String titulo;
    private int total;
    private int disponibles;

    public Libro(String isbn, String titulo, int total, int disponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.total = total;
        this.disponibles = disponibles;
    }

    public String getIsbn() {
         return isbn; 
        }
    public String getTitulo() { 
        return titulo; 
    }
    public int getTotal() { 
        return total; 
    }
    public int getDisponibles() { 
        return disponibles; 
    }

    public void incrementar(int cant) {
        if (cant < 0) throw new IllegalArgumentException("cant negativa");
        this.total += cant;
        this.disponibles += cant;
    }

    public int prestar(int cant) {
        if (cant <= 0) return 0;
        int efect = Math.min(cant, disponibles);
        disponibles -= efect;
        return efect;
    }

    public void devolver(int cant) {
        if (cant <= 0) return;
        disponibles = Math.min(total, disponibles + cant);
    }

    public boolean retirables() {
        return disponibles == total;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | total=%d disp=%d", isbn, titulo, total, disponibles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
