package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        final int capacitatAparcament = 3;
        final ParkingLot aparcament = new ParkingLot(capacitatAparcament);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable cotxe1 = () -> {
            aparcament.entraCotxe("Cotxe 1");
            aparcament.surtCotxe("Cotxe 1");
        };

        Runnable cotxe2 = () -> {
            aparcament.entraCotxe("Cotxe 2");
            aparcament.surtCotxe("Cotxe 2");
        };

        Runnable cotxe3 = () -> {
            aparcament.entraCotxe("Cotxe 3");
            aparcament.surtCotxe("Cotxe 3");
        };

        Runnable cotxe4 = () -> {
            aparcament.entraCotxe("Cotxe 4");
            aparcament.surtCotxe("Cotxe 4");
        };

        Runnable cotxe5 = () -> {
            aparcament.entraCotxe("Cotxe 5");
            aparcament.surtCotxe("Cotxe 5");
        };

        executor.submit(cotxe1);
        executor.submit(cotxe2);
        executor.submit(cotxe3);
        executor.submit(cotxe4);
        executor.submit(cotxe5);

        executor.shutdown();

        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulació de l'aparcament completada.");
    }
}
