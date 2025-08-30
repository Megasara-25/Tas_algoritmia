package edu.ucu.aed.ta2.lista;

public class Libro implements Comparable<Libro> {
    private final String codigo;     // codigo interno
    private String titulo;
    private double precioReposicion;
    private int stock;

    public Libro(String codigo, String titulo, double precioReposicion, int stock) {
        this.codigo = codigo.trim();
        this.titulo = titulo.trim();
        this.precioReposicion = precioReposicion;
        this.stock = Math.max(0, stock);
    }

    public String getCodigo() {
        return codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public double getPrecioReposicion() {
        return precioReposicion;
    }
    public int getStock() {
        return stock;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo.trim();
    }
    public void setPrecioReposicion(double precioReposicion) {
        this.precioReposicion = precioReposicion;
    }

    public void agregar(int cantidad) {
        this.stock += Math.max(0, cantidad);
    }
    public int prestar(int cantidad) {
        int efectivamentePrestado = Math.min(Math.max(0, cantidad), this.stock);
        this.stock -= efectivamentePrestado;
        return efectivamentePrestado;
    }
    public void devolver(int cantidad) { this.stock += Math.max(0, cantidad); }

    @Override
    public int compareTo(Libro o) {
        return this.titulo.compareToIgnoreCase(o.titulo);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | $%.2f | stock=%d", titulo, codigo, precioReposicion, stock);
    }
    
}
