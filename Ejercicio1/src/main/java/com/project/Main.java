package com.project;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        final double[] dades = {10.5, 7.8, 6.2, 4.5, 9.1, 5.0, 8.4, 3.7, 6.9, 7.3};

        final double[] suma = {0};
        final double[] mitjana = {0};
        final double[] desviacioEstandard = {0};

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Tots els càlculs han acabat.");
            System.out.println("Suma: " + suma[0]);
            System.out.println("Mitjana: " + mitjana[0]);
            System.out.println("Desviació estàndard: " + desviacioEstandard[0]);
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable tascaSuma = () -> {
            try {
                System.out.println("Calculant la suma...");
                suma[0] = Arrays.stream(dades).sum();
                barrier.await(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable tascaMitjana = () -> {
            try {
                System.out.println("Calculant la mitjana...");
                mitjana[0] = Arrays.stream(dades).average().orElse(0);
                barrier.await(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable tascaDesviacioEstandard = () -> {
            try {
                System.out.println("Calculant la desviació estàndard...");
                double mitja = Arrays.stream(dades).average().orElse(0);
                desviacioEstandard[0] = Math.sqrt(Arrays.stream(dades)
                        .map(d -> Math.pow(d - mitja, 2))
                        .average().orElse(0));
                barrier.await(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        executor.submit(tascaSuma);
        executor.submit(tascaMitjana);
        executor.submit(tascaDesviacioEstandard);

        executor.shutdown();

        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Càlcul complet.");
    }
}
