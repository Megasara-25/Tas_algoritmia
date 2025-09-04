package edu.ucu.aed.ta04;

public interface TDALista<E> {
    boolean eliminar(E clave);     // quitar y destruir (no retorna dato)
    E quitar(E clave);             // retirar sin destruir (retorna dato o null)
    E quitarEn(int k);             // quita por posición y retorna dato
    int eliminarTodos(E clave);    // elimina todas las ocurrencias
    void agregarAlFinal(E e);
    int tamaño();
    boolean esVacia();
}
