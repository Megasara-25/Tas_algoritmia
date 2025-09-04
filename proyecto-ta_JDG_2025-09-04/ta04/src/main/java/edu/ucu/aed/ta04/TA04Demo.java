package edu.ucu.aed.ta04;

public class TA04Demo {
    public static void main(String[] args) {
        ListaEnlazada<Integer> L = new ListaEnlazada<>();
        L.agregarAlFinal(1);
        L.agregarAlFinal(2);
        L.agregarAlFinal(2);
        L.agregarAlFinal(3);
        System.out.println("L: " + L);

        System.out.println("quitar(2) -> " + L.quitar(2)); // retorna 2
        System.out.println("L: " + L);

        System.out.println("eliminar(3) -> " + L.eliminar(3)); // true
        System.out.println("L: " + L);

        System.out.println("quitarEn(0) -> " + L.quitarEn(0)); // 1
        System.out.println("L: " + L);

        System.out.println("eliminarTodos(2) -> " + L.eliminarTodos(2)); // según restos
        System.out.println("L: " + L + " (tamaño=" + L.tamaño() + ")");
    }
}
