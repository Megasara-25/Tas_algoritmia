package edu.ucu.aed.ta04;

public class ListaEnlazada<E> implements TDALista<E> {

    private static final class Nodo<E> {
        E dato; Nodo<E> sig;
        Nodo(E d) { this.dato = d; }
    }
    private Nodo<E> cabeza;
    private int n;

    @Override public void agregarAlFinal(E e) {
        Nodo<E> x = new Nodo<>(e);
        if (cabeza == null) { cabeza = x; n=1; return; }
        Nodo<E> p = cabeza;
        while (p.sig != null) p = p.sig;
        p.sig = x; n++;
    }

    @Override public boolean eliminar(E clave) {
        if (cabeza == null) return false;
        if ((cabeza.dato==null && clave==null) || (cabeza.dato!=null && cabeza.dato.equals(clave))) {
            cabeza = cabeza.sig; n--; return true;
        }
        Nodo<E> ant = cabeza, act = cabeza.sig;
        while (act != null && !equalsDato(act.dato, clave)) { ant = act; act = act.sig; }
        if (act == null) return false;
        ant.sig = act.sig; n--; return true;
    }

    @Override public E quitar(E clave) {
        if (cabeza == null) return null;
        if (equalsDato(cabeza.dato, clave)) {
            E out = cabeza.dato; cabeza = cabeza.sig; n--; return out;
        }
        Nodo<E> ant = cabeza, act = cabeza.sig;
        while (act != null && !equalsDato(act.dato, clave)) { ant = act; act = act.sig; }
        if (act == null) return null;
        ant.sig = act.sig; n--; return act.dato;
    }

    @Override public E quitarEn(int k) {
        if (k < 0 || k >= n) return null;
        if (k == 0) { E out = cabeza.dato; cabeza = cabeza.sig; n--; return out; }
        int i = 0;
        Nodo<E> ant = null, act = cabeza;
        while (i < k) { ant = act; act = act.sig; i++; }
        ant.sig = act.sig; n--; return act.dato;
    }

    @Override public int eliminarTodos(E clave) {
        int c = 0;
        while (cabeza != null && equalsDato(cabeza.dato, clave)) { cabeza = cabeza.sig; n--; c++; }
        if (cabeza == null) return c;
        Nodo<E> ant = cabeza, act = cabeza.sig;
        while (act != null) {
            if (equalsDato(act.dato, clave)) { ant.sig = act.sig; n--; c++; act = ant.sig; }
            else { ant = act; act = act.sig; }
        }
        return c;
    }

    private boolean equalsDato(E a, E b) {
        return (a==null && b==null) || (a!=null && a.equals(b));
    }

    @Override public int tamaño() { return n; }
    @Override public boolean esVacia() { return n==0; }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<E> p = cabeza;
        while (p != null) {
            sb.append(p.dato);
            if (p.sig != null) sb.append(" -> ");
            p = p.sig;
        }
        return sb.toString();
    }
}
