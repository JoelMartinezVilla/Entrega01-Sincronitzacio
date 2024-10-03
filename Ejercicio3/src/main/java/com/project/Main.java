package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        // Definir el nombre màxim de connexions simultànies
        final int maxConnexions = 3;
        final WebPage webPage = new WebPage(maxConnexions);

        // ExecutorService per gestionar les connexions dels usuaris
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Definir les tasques que simulen les connexions dels usuaris
        Runnable user1 = () -> {
            webPage.connecta("Usuari 1");
            webPage.desconnecta("Usuari 1");
        };

        Runnable user2 = () -> {
            webPage.connecta("Usuari 2");
            webPage.desconnecta("Usuari 2");
        };

        Runnable user3 = () -> {
            webPage.connecta("Usuari 3");
            webPage.desconnecta("Usuari 3");
        };

        Runnable user4 = () -> {
            webPage.connecta("Usuari 4");
            webPage.desconnecta("Usuari 4");
        };

        Runnable user5 = () -> {
            webPage.connecta("Usuari 5");
            webPage.desconnecta("Usuari 5");
        };

        // Simular connexions concurrents
        executor.submit(user1);
        executor.submit(user2);
        executor.submit(user3);
        executor.submit(user4);
        executor.submit(user5);

        // Tancar l'executor
        executor.shutdown();

        try {
            // Esperar que totes les connexions acabin abans de continuar
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulació del servidor web completada.");
    }
}