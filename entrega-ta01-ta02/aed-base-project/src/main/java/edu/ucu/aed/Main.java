package edu.ucu.aed;

import edu.ucu.aed.utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        FileUtils.leerLineas("./test.txt", x -> {
            System.out.println(x);
        });

    }
}