package edu.ucu.aed.ta01;

public class TA01ListOps {

    public static final class Nodo {
        int valor;
        Nodo siguiente;
        Nodo(int v) { this.valor = v; }
    }

    public static final class ListaSimple {
        private Nodo cabeza;

        // Insertar al inicio – O(1)
        public void insertarAlInicio(int x) {
            Nodo n = new Nodo(x);
            n.siguiente = cabeza;
            cabeza = n;
        }

        // Insertar al final – O(n)
        public void insertarAlFinal(int x) {
            Nodo n = new Nodo(x);
            if (cabeza == null) { cabeza = n; return; }
            Nodo p = cabeza;
            while (p.siguiente != null) p = p.siguiente;
            p.siguiente = n;
        }

        // Buscar por valor – O(n)
        public Nodo buscar(int clave) {
            Nodo p = cabeza;
            while (p != null && p.valor != clave) p = p.siguiente;
            return p;
        }

        // Eliminar por valor (primera ocurrencia) – O(n)
        public boolean eliminar(int clave) {
            if (cabeza == null) return false;
            if (cabeza.valor == clave) { cabeza = cabeza.siguiente; return true; }
            Nodo ant = cabeza, act = cabeza.siguiente;
            while (act != null && act.valor != clave) {
                ant = act; act = act.siguiente;
            }
            if (act == null) return false;
            ant.siguiente = act.siguiente;
            return true;
        }

        public int tamaño() {
            int c = 0; Nodo p = cabeza;
            while (p != null) { c++; p = p.siguiente; }
            return c;
        }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            Nodo p = cabeza;
            while (p != null) {
                sb.append(p.valor);
                if (p.siguiente != null) sb.append(" -> ");
                p = p.siguiente;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        ListaSimple L = new ListaSimple();
        L.insertarAlInicio(3); // O(1)
        L.insertarAlFinal(7);  // O(n)
        L.insertarAlFinal(9);
        System.out.println("Lista: " + L);
        System.out.println("Buscar 7: " + (L.buscar(7) != null));
        System.out.println("Eliminar 3: " + L.eliminar(3));
        System.out.println("Lista: " + L + " (tamaño=" + L.tamaño() + ")");
    }
}
