package com.project;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ParkingLot {

    private final Semaphore semaphore;

    public ParkingLot(int capacitat) {
        this.semaphore = new Semaphore(capacitat); 
    }

    public void entraCotxe(String cotxe) {
        try {
            if (semaphore.availablePermits() == 0) {
                System.out.println(cotxe + " està esperant perquè l'aparcament està ple.");
            }
            semaphore.acquire();
            System.out.println(cotxe + " ha entrat a l'aparcament.");
            TimeUnit.SECONDS.sleep(2); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void surtCotxe(String cotxe) {
        try {
            System.out.println(cotxe + " està sortint de l'aparcament.");
            semaphore.release(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

