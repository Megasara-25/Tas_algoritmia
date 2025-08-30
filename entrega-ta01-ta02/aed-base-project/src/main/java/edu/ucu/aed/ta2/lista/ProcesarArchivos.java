package edu.ucu.aed.ta2.lista;

import edu.ucu.aed.utils.FileUtils;

public class ProcesarArchivos {

    public static double procesarAdquisiciones(Biblioteca b) {
        final double[] totalAgregado = {0.0};
        FileUtils.leerLineas("adquisiciones.txt", linea -> {
            String[] partes = linea.split(",");
            if (partes.length < 4) return;
            String codigo = partes[0].trim();
            String titulo = partes[1].trim();
            double precio = Double.parseDouble(partes[2].trim());
            int cantidad = Integer.parseInt(partes[3].trim());
            b.agregarEjemplares(codigo, titulo, precio, cantidad);
            totalAgregado[0] += precio * cantidad;
        });
        return totalAgregado[0];
    }

    public static int procesarPrestamos(Biblioteca b) {
        final int[] variacionPrestados = {0};
        FileUtils.leerLineas("prestamos.txt", linea -> {
            String[] partes = linea.split(",");
            if (partes.length < 3) return;
            String codigo = partes[0].trim();
            String tipo = partes[1].trim().toUpperCase();
            int cantidad = Integer.parseInt(partes[2].trim());
            switch (tipo) {
                case "PRESTAMO":
                    int efect = b.prestar(codigo, cantidad);
                    variacionPrestados[0] += efect;
                    break;
                case "DEVOLUCION":
                    b.devolver(codigo, cantidad);
                    variacionPrestados[0] -= cantidad;
                    break;
            }
        });
        return variacionPrestados[0];
    }
}
