package edu.ucu.aed.ta05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class SucursalesApp {

    private final LinkedList<String> lista = new LinkedList<>();

    public void cargarDesdeRecurso(String resource) throws IOException {
        try (InputStream is = SucursalesApp.class.getClassLoader().getResourceAsStream(resource)) {
            if (is == null) throw new IOException("Recurso no encontrado: " + resource);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String ciudad = line.trim();
                    if (!ciudad.isEmpty() && !ciudad.startsWith("#")) lista.add(ciudad);
                }
            }
        }
    }

    public int buscar(String ciudad) {
        for (int i = 0; i < lista.size(); i++) if (lista.get(i).equalsIgnoreCase(ciudad)) return i;
        return -1;
    }

    public boolean quitar(String ciudad) {
        int i = buscar(ciudad);
        if (i == -1) return false;
        lista.remove(i);
        return true;
    }

    public String siguienteA(String ciudad) {
        int i = buscar(ciudad);
        if (i == -1 || i+1 >= lista.size()) return null;
        return lista.get(i+1);
    }

    public void imprimirTodoYTotal() {
        for (String c : lista) System.out.println(c);
        System.out.println("Total: " + lista.size());
    }

    public static void main(String[] args) throws Exception {
        SucursalesApp app = new SucursalesApp();
        app.cargarDesdeRecurso("sucursales.txt");
        app.imprimirTodoYTotal();

        // Pruebas pedidas
        System.out.println("\nEliminar 'Chicago': " + app.quitar("Chicago"));
        System.out.println("Siguiente a 'Hong Kong': " + app.siguienteA("Hong Kong"));
    }
}
