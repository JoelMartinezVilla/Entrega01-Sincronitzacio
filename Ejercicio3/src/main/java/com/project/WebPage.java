package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class WebPage {

    // Semàfor per limitar les connexions simultànies
    private final Semaphore semaphore;

    // Constructor per establir el nombre màxim de connexions simultànies
    public WebPage(int maxConnexions) {
        this.semaphore = new Semaphore(maxConnexions); // Capacitat màxima de connexions
    }

    // Mètode per simular una connexió a la pàgina web
    public void connecta(String user) {
        try {
            if (semaphore.availablePermits() == 0) {
                System.out.println(user + " està esperant per connectar-se perquè s'ha superat el límit de connexions.");
            }
            semaphore.acquire(); // Adquirir permís per connectar-se a la pàgina
            System.out.println(user + " s'ha connectat a la pàgina web.");
            TimeUnit.SECONDS.sleep(2); // Simular temps que l'usuari està connectat
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Mètode per simular la desconnexió de la pàgina web
    public void desconnecta(String user) {
        try {
            System.out.println(user + " s'ha desconnectat de la pàgina web.");
            semaphore.release(); // Alliberar permís, permetent que altres usuaris es connectin
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
