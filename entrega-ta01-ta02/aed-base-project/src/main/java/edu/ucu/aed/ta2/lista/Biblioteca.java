package edu.ucu.aed.ta2.lista;

import edu.ucu.aed.tda.lista.TDALista;
import edu.ucu.aed.tda.lista.impl.Lista;

public class Biblioteca {
    private final TDALista<Libro> catalogo = new Lista<>();

    public boolean altaLibro(Libro libro) {
        return catalogo.insertar(libro);
    }

    public Libro buscarPorCodigo(String codigo) {
        return catalogo.buscar(o -> codigo.compareTo(((Libro)o).getCodigo()));
    }

    public void agregarEjemplares(String codigo, String titulo, double precio, int cantidad) {
        Libro l = buscarPorCodigo(codigo);
        if (l == null) {
            altaLibro(new Libro(codigo, titulo, precio, cantidad));
        } else {
            l.setTitulo(titulo);
            l.setPrecioReposicion(precio);
            l.agregar(cantidad);
        }
    }

    public int prestar(String codigo, int cantidad) {
        Libro l = buscarPorCodigo(codigo);
        if (l == null) return 0;
        return l.prestar(cantidad);
    }

    public void devolver(String codigo, int cantidad) {
        Libro l = buscarPorCodigo(codigo);
        if (l != null) l.devolver(cantidad);
    }

    public boolean retirar(String codigo) {
        return catalogo.eliminar(o -> codigo.compareTo(((Libro)o).getCodigo())) != null;
    }

    public int stock(String codigo) {
        Libro l = buscarPorCodigo(codigo);
        return l == null ? -1 : l.getStock();
    }

    public String listarPorTitulo() {
        return catalogo.imprimir("\n");
    }
}
