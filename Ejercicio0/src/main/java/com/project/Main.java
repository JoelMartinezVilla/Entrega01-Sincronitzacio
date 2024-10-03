package com.project;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        final String[] results = new String[3];

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            String resultatFinal = results[0] + " " + results[1] + " " + results[2];
            System.out.println("Tots els microserveis han acabat. Resultat final: " + resultatFinal);
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable microservei1 = () -> {
            try {
                System.out.println("Microservei 1 processant dades...");
                TimeUnit.SECONDS.sleep(2); 
                results[0] = "Resultat del Microservei 1";
                barrier.await(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable microservei2 = () -> {
            try {
                System.out.println("Microservei 2 processant dades...");
                TimeUnit.SECONDS.sleep(3); 
                results[1] = "Resultat del Microservei 2";
                barrier.await(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable microservei3 = () -> {
            try {
                System.out.println("Microservei 3 processant dades...");
                TimeUnit.SECONDS.sleep(1); 
                results[2] = "Resultat del Microservei 3";
                barrier.await(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        executor.submit(microservei1);
        executor.submit(microservei2);
        executor.submit(microservei3);

        executor.shutdown();

        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Procés complet.");
    }
}
