package edu.ucu.aed.ta02;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {

    private final Map<String, Libro> catalogo = new HashMap<>();

    public void incorporarOIncrementar(String isbn, String titulo, int cantidad) {
        if (cantidad <= 0) return;
        catalogo.compute(isbn, (k, v) -> {
            if (v == null) return new Libro(isbn, titulo, cantidad, cantidad);
            v.incrementar(cantidad);
            return v;
        });
    }

    public int prestar(String isbn, int cantidad) {
        Libro l = catalogo.get(isbn);
        if (l == null) return 0;
        return l.prestar(cantidad);
    }

    public void devolver(String isbn, int cantidad) {
        Libro l = catalogo.get(isbn);
        if (l == null) return;
        l.devolver(cantidad);
    }

    public boolean retirar(String isbn) {
        Libro l = catalogo.get(isbn);
        if (l == null) return false;
        if (!l.retirables()) return false;
        catalogo.remove(isbn);
        return true;
    }

    public Optional<Libro> consultar(String isbn) {
        return Optional.ofNullable(catalogo.get(isbn));
    }

    public List<Libro> listarPorTitulo() {
        return catalogo.values().stream()
                .sorted(Comparator.comparing(Libro::getTitulo, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // Procesamiento de archivos
    // Formatos sugeridos:
    // adquisiciones.txt ->  ADD;ISBN;TITULO;CANT;PRECIO_UNIT  |  ADD_STOCK;ISBN;CANT;PRECIO_UNIT
    public BigDecimal procesarAdquisiciones(Path archivo) throws IOException {
        BigDecimal totalAgregado = BigDecimal.ZERO;
        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank() || line.trim().startsWith("#")) continue;
                String[] p = line.split(";", -1);
                String op = p[0].trim().toUpperCase(Locale.ROOT);
                if ("ADD".equals(op)) {
                    String isbn = p[1].trim();
                    String titulo = p[2].trim();
                    int cant = Integer.parseInt(p[3].trim());
                    BigDecimal precio = new BigDecimal(p[4].trim());
                    incorporarOIncrementar(isbn, titulo, cant);
                    totalAgregado = totalAgregado.add(precio.multiply(BigDecimal.valueOf(cant)));
                } else if ("ADD_STOCK".equals(op)) {
                    String isbn = p[1].trim();
                    int cant = Integer.parseInt(p[2].trim());
                    BigDecimal precio = new BigDecimal(p[3].trim());
                    String titulo = catalogo.containsKey(isbn) ? catalogo.get(isbn).getTitulo() : "SIN_TITULO";
                    incorporarOIncrementar(isbn, titulo, cant);
                    totalAgregado = totalAgregado.add(precio.multiply(BigDecimal.valueOf(cant)));
                }
            }
        }
        return totalAgregado.setScale(2, RoundingMode.HALF_UP);
    }

    // prestamos.txt -> PRESTAR;ISBN;CANT  | DEVOLVER;ISBN;CANT | RETIRAR;ISBN | CONSULTAR;ISBN
    public int procesarMovimientos(Path archivo) throws IOException {
        int variacionPrestamos = 0;
        try (BufferedReader br = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank() || line.trim().startsWith("#")) continue;
                String[] p = line.split(";", -1);
                String op = p[0].trim().toUpperCase(Locale.ROOT);
                switch (op) {
                    case "PRESTAR": {
                        String isbn = p[1].trim();
                        int cant = Integer.parseInt(p[2].trim());
                        int efect = prestar(isbn, cant);
                        variacionPrestamos += efect;
                        break;
                    }
                    case "DEVOLVER": {
                        String isbn = p[1].trim();
                        int cant = Integer.parseInt(p[2].trim());
                        devolver(isbn, cant);
                        variacionPrestamos -= cant;
                        break;
                    }
                    case "RETIRAR": {
                        String isbn = p[1].trim();
                        retirar(isbn);
                        break;
                    }
                    case "CONSULTAR": {
                        String isbn = p[1].trim();
                        consultar(isbn).ifPresent(l -> System.out.println("CONSULTA: " + l));
                        break;
                    }
                    default:
                        // ignorar
                }
            }
        }
        return variacionPrestamos;
    }

    public static Path resourcePath(String name) throws URISyntaxException {
        try {
            return Paths.get(Objects.requireNonNull(Biblioteca.class.getClassLoader().getResource(name)).toURI());
        } catch (NullPointerException ex) {
            // fallback a ruta relativa si se corre fuera de empaquetado
            return Paths.get("src/main/resources").resolve(name).toAbsolutePath();
        }
    }

    public static void main(String[] args) throws Exception {
        Biblioteca b = new Biblioteca();
        Path adquisiciones = args.length > 0 ? Paths.get(args[0]) : resourcePath("adquisiciones.txt");
        Path prestamos = args.length > 1 ? Paths.get(args[1]) : resourcePath("prestamos.txt");

        System.out.println("Procesando adquisiciones desde: " + adquisiciones);
        var total = b.procesarAdquisiciones(adquisiciones);
        System.out.println("Total agregado al stock (monetario): " + total);

        System.out.println("Procesando movimientos desde: " + prestamos);
        int varPrestamos = b.procesarMovimientos(prestamos);
        System.out.println("Variación neta de ejemplares prestados: " + varPrestamos);

        System.out.println("\nListado por título:");
        for (Libro l : b.listarPorTitulo()) System.out.println(" - " + l);
    }
}
