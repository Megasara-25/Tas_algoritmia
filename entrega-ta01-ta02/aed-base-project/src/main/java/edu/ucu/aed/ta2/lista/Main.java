package edu.ucu.aed.ta2.lista;

public class Main {
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca();

        double totalAgregado = ProcesarArchivos.procesarAdquisiciones(b);
        int variacionPrestamos = ProcesarArchivos.procesarPrestamos(b);

        System.out.println("            Biblioteca  UCU        ");
        System.out.println("--------------------------------------------------\n");

        System.out.printf("%-30s %10.2f%n", " Total agregado al stock:", totalAgregado);
        System.out.printf("%-30s %10d%n", " Variación total de prestamos:", variacionPrestamos);

        System.out.println("\n--------------------------------------------");
        System.out.println("          Catalogo de Libros        ");
        System.out.println("--------------------------------------------");
        System.out.printf("%-40s %-20s %-20s %-10s%n",
                          "Título", "Código", "Precio", "Stock");
        System.out.println("=================================================================================================================================");

        // imprimir catalogo
        b.listarPorTitulo().lines().forEach(linea -> {
            
            String[] partes = linea.split("\\|");
            if (partes.length == 4) {
                String titulo = partes[0].trim();
                String codigo = partes[1].trim();
                String precio = partes[2].trim();
                String stock = partes[3].replace("stock=", "").trim();

                System.out.printf("%-40s %-20s %-20s %-10s%n",
                        titulo, codigo, precio, stock);
            } else {
                System.out.println(linea);
            }
        });

        System.out.println("===================================================================================================================\n");

    }
}
