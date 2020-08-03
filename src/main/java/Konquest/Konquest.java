/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;

import Planeta.Planeta;
import Planeta.Tierra;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author dany
 */
public class Konquest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Partida juego = new Partida();
        Menu();

    }

    public static void Menu() {
        Partida partida = new Partida();

        Scanner entrada = new Scanner(System.in);
        int tamanioX, tamanioY;

        boolean salir = false;
        int opcion;                         //Guardarla opcion del usuario

        while (!salir) {
            System.out.println("******************************************************");
            System.out.println("\033[35mKonkest");
            System.out.println("\033[30m******************************************************");
            System.out.println("1. Disenio de mapa");
            System.out.println("2. Jugar");
            System.out.println("0. Salir");

            try {                           //verificar si el usuario escribe un numero

                System.out.println("Escribe una de las opciones");
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                      partida.menuDisenioMapa();
                        
                        break;
                    case 2:
                        partida.acciones();
                        break;
                                 
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                entrada.next();
            }
        }
    }

}
