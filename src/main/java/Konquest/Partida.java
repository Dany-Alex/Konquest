/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;

import Constructor.*;
import Nave.*;
import Planeta.*;

import Planeta.Planeta;

import java.security.spec.PKCS8EncodedKeySpec;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author dany
 */
public class Partida {

    public static final String colorNegro = "\u001B[30m";
    public static final String colorRojo = "\u001B[31m";
    public static final String colorVerde = "\u001B[32m";
    public static final String coloreAmarillo = "\u001B[33m";
    public static final String colorAzul = "\u001B[34m";
    public static final String colorPurpura = "\u001B[35m";
    public static final String colorCyan = "\u001B[36m";
    public static final String colorBlanco = "\u001B[37m";
    public static final String colorReset = "\u001B[0m";

    public static int contadorFila = 0;
    public static int contadorColumna = 0;
    public String[][] tablero;
    public boolean turno = false;
    public int contadorTurno = 0;
    public String[] letras = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Planeta[][] planeta;
    public Planeta[] miPlanetas;
    public Constructor[] constructor;
    public Planeta inicio = new Planeta(" ", 0, 0, 0, 0);
    public String[] nombreJugador = new String[2];
    public String propietario;
    public Jugador jugador1 = new Jugador(1, "Jugador 1", 0, miPlanetas);
    public Jugador jugador2 = new Jugador(2, "Jugador 2", 0, miPlanetas);

    ;
    public Partida() {

//     Tablero teb = new Tablero(10, 10);
//    teb.imprimirTablero();
    }

    int tamanioX = 0, tamanioY;
    int nuevoTamanioX, nuevoTamanioY;

    public void menuDisenioMapa() {
        Partida partida = new Partida();

        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        int opcion;                         //Guardarla opcion del usuario

        while (!salir) {
            System.out.println("******************************************************");
            System.out.println(colorPurpura + "Disenia Mapa" + colorReset);
            System.out.println("******************************************************");
            System.out.println("1. Crear dimensiones del Mapa");
            System.out.println("2. Agregar planetas Modo: aleatorio");
            System.out.println("3. Ver Mapa");
            System.out.println("4. Ingresar nombre de jugadores");
            System.out.println("0. Salir");

            try {                           //verificar si el usuario escribe un numero

                System.out.println("Escribe una de las opciones");
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:

                        System.out.println("Escribe el numero de columnas del mapa");
                        tamanioX = entrada.nextInt();
                        System.out.println("Escribe el numero de filas del mapa");
                        tamanioY = entrada.nextInt();

                        nuevoTamanioX = tamanioX + 1;
                        nuevoTamanioY = tamanioY + 1;
                        planeta = new Planeta[nuevoTamanioX][nuevoTamanioY];

                        ImprimirTablero(planeta);

                        break;
                    case 2:

                        System.out.println("Escribe el numero de planetas a generar en el mapa");

                        int numeroPlanetas = entrada.nextInt();
                        llenarMatrizPlaneta(tamanioX, numeroPlanetas);
                        int count = 0;

                        for (int i = 0; i < planeta.length; i++) {
                            for (int j = 0; j < planeta[i].length; j++) {

                                if (count < 3) {
                                    if (planeta[i][j] != null) {
                                        planeta[i][j].setPropietario(count);
                                        count++;
                                    }
                                }

                            }
                        }
                        imprimirMatriz(planeta);

                        break;

                    case 3:
                        ImprimirTablero(planeta);
                        imprimirMatriz(planeta);
                        break;
                    case 4:
                        ingresarNombres();

                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                entrada.next();
            }
        }
    }

    public void acciones() {
        String coordenada;
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        int opcion;
        while (!salir) {
            System.out.println("******************************************************");
            System.out.println(colorPurpura + "Acciones" + colorReset);
            System.out.println("******************************************************");
            indicarTurno();
            System.out.println("******************************************************");
            System.out.println("1. Medir Distancia");
            System.out.println("2. Consultar Planeta");
            System.out.println("3. Tineda");
            System.out.println("4. Consultar Flota");
            System.out.println("5. Enviar Flota");
            System.out.println("6. Construir Nave");
            System.out.println("7. Terminar Turno");
            System.out.println("0. Salir");
            System.out.println("******************************************************");
            try {                           //verificar si el usuario escribe un numero

                System.out.println("Escribe una de las opciones");
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        ImprimirTablero(planeta);
                        imprimirMatriz(planeta);

                        entrada.nextLine();
                        System.out.println("Ingresar la Coordenadas del planeta de origen y destino separadas por , ");
                        coordenada = entrada.nextLine();
                        System.out.println(medirDistancia(coordenada));
                        break;
                    case 2:
                        ImprimirTablero(planeta);
                        imprimirMatriz(planeta);

                        entrada.nextLine();
                        System.out.println("Ingresar la Coordenadas del planeta");
                        coordenada = entrada.nextLine();
                        int origenOutX = coordenadaX(coordenada, 0);
                        int origenOutY = coordenadaY(coordenada, 0);
                        System.out.println("(" + origenOutX + "'" + origenOutY + ")");
                        System.out.println(turnoColor() + planeta[origenOutX][origenOutY].imprimirIformacionPlaneta(planeta[origenOutX][origenOutY]) + colorReset);

                        break;

                    case 3:
                        tienda();
                        break;
                    case 4:
                        consultarFlota();
                        break;
                    case 5:
                        ImprimirTablero(planeta);
                        imprimirMatriz(planeta);

                        entrada.nextLine();
                        System.out.println("Ingresar la Coordenadas con el siguente formato:(coordenada origen,cantidad gerreros, tipo de guerro,tipo de nave,coordenada origen)");
                        System.out.println("Ejemplo: A4,35,Mole,Naboo N-1,A5 ");
                        String coordenadaFlota = entrada.nextLine();

                        int origenOutXFlota = coordenadaX(coordenadaFlota, 0);
                        int origenOutYFlota = coordenadaY(coordenadaFlota, 0);

                        int cantidadGerreros = Integer.valueOf(coordenadaFlota.split(",")[1]);
                        String tipoGerrero = coordenadaFlota.split(",")[2];
                        String tipoNave = coordenadaFlota.split(",")[3];

                        int destinoOutXFlota = coordenadaX(coordenadaFlota, 4);
                        int destinoOutYFlota = coordenadaY(coordenadaFlota, 4);
                        System.out.println("Origen");
                        System.out.println("(" + origenOutXFlota + "'" + origenOutYFlota + ")");
                        System.out.println("Destino");
                        System.out.println("(" + destinoOutXFlota + "'" + destinoOutYFlota + ")");
                        System.out.println("cantida gerreros " + cantidadGerreros);
                        System.out.println("tipo gerreros " + tipoGerrero);
                        System.out.println("tipo nave " + tipoNave);

                        int distancia = (int) Math.round(Math.hypot((destinoOutXFlota - origenOutXFlota), (destinoOutYFlota - origenOutYFlota)));
                        int distnciaTotal = distancia + contadorTurno;
                        int elegirFlota = 0;
                        consultarFlota();
                        System.out.println("elige las el numero de nave ");

                        elegirFlota = entrada.nextInt();

                        System.out.println(planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].toString());
                        planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].setContadorTurno(distnciaTotal);
                        planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].setOcupado(true);
                        planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].setNombrePlanetaOrigen(planeta[origenOutXFlota][origenOutYFlota].getNombre());
                        planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].setNombrePlanetaDestino(planeta[destinoOutXFlota][destinoOutYFlota].getNombre());

                        for (int i = 0; i < planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].gerreros.length; i++) {
                            System.out.println(planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].gerreros[i]);
                        }

                        System.out.println(planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].toString());
                        System.out.println("la ve llegara en el turno: " + planeta[origenOutXFlota][origenOutYFlota].arregloNave[elegirFlota].getContadorTurno());

                        break;

                    case 6:

                        break;
                    case 7:
                        cambiarTurno();
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 7");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                entrada.next();
            }
        }

    }

    public boolean verificarLlegada() {

        return false;
    }

    public int obtenerOrigenX(int OrigenX) {
        return OrigenX;
    }

    public int obtenerOrigenY(int OrigenY) {
        return OrigenY;
    }

    public int obtenerDestinoX(int DestinoX) {
        return DestinoX;
    }

    public int obtenerDestinoY(int DestinoY) {
        return DestinoY;
    }

    public void conquista() {
        System.out.println("-*-*-*-*-*-**-*-*-Inicio de batalla-*-*-*-*-*-*-");

        int numero;
        if (isTurno() == false) {
            numero = 1;
        } else {
            numero = 2;
        }

        for (int i = 0; i < planeta.length; i++) {
            for (int j = 0; j < planeta[i].length; j++) {
                if (planeta[i][j] != null) {
                    if (planeta[i][j].propietario == numero) {
                        for (int k = 0; k < planeta[i][j].arregloNave.length; k++) {
                            if (planeta[i][j].arregloNave[k] != null) {
                                if (planeta[i][j].arregloNave[k].ocupado == true) {
                                    if (planeta[i][j].arregloNave[k].contadorTurno == contadorTurno) {
                                        boolean salir = false;
                                        do {
                                            if (planeta[i][j].arregloNave.length == 0) {

                                            } else {
                                            }

                                        } while (!salir);
                                    } else {

                                    }
                                }

                            }

                        }
                    }
                }
            }
        }

        System.out.println("Fin de batalla");
//        for (int i = 0; i < cantidad; i++) {
//            heroes[1].ataque(bestias[1]);
//            bestias[1].ataque(heroes[1]);
//        }
    }

    public void consultarFlota() {
        int numero;
        if (isTurno() == false) {
            numero = 1;
        } else {
            numero = 2;
        }

        for (int i = 0; i < planeta.length; i++) {
            for (int j = 0; j < planeta[i].length; j++) {
                if (planeta[i][j] != null) {
                    if (planeta[i][j].propietario == numero) {
                        for (int k = 0; k < planeta[i][j].arregloNave.length; k++) {
                            if (planeta[i][j].arregloNave[k] != null) {
                                if (planeta[i][j].arregloNave[k].ocupado == true) {
                                    System.out.println(planeta[i][j].arregloNave[k].toString());

                                    System.out.println("posisicion; " + k);
                                } else {
                                    System.out.println("no esta vijando posisicion #" + k);
                                }

                            }

                        }
                    }
                }
            }
        }
    }

    public int obtenerCoordenada(String origen) {
        int posicion = 0;
        for (int i = 0; i < letras.length; i++) {
            if (letras[i].equals(origen)) {
                posicion = (i);
                return posicion;
            }
        }
        return posicion;
    }

    public int coordenadaX(String coordenada, int out) {
        String cooredenadaA = coordenada.split(",")[out];
        String origenInX = cooredenadaA.replaceAll("[^a-zA-Z]", "").toUpperCase();
        int origenOutX = obtenerCoordenada(origenInX);
        return origenOutX - 1;
    }

    public int coordenadaY(String coordenada, int out) {
        String cooredenadaA = coordenada.split(",")[out];

        String origenInY = cooredenadaA.replaceAll("[^0-9]", "");
        Integer origenOutY = Integer.valueOf(origenInY);
        return origenOutY - 1;
    }

    public double medirDistancia(String coordenada) {
        int origenOutX, origenOutY, destinoOutX, destinoOutY;
        double distancia = 0;
        origenOutX = coordenadaX(coordenada, 0);
        origenOutY = coordenadaY(coordenada, 0);
        destinoOutX = coordenadaX(coordenada, 1);
        destinoOutY = coordenadaY(coordenada, 1);

        distancia = Math.round(Math.hypot((destinoOutX - origenOutX), (destinoOutY - origenOutY)));

        System.out.println("(" + origenOutX + "," + origenOutY + ")");
        System.out.println("(" + destinoOutX + "," + destinoOutY + ")");

        return distancia;

    }

    public void indicarTurno() {
        System.out.println(colorPurpura + "Tunorno No.: " + contadorTurno + colorReset);
        if (isTurno() == false) {
            System.out.println(colorVerde + jugador1.getNombre() + " : Elija que acciones desea hacer." + colorReset);
        } else {
            System.out.println(colorAzul + jugador2.getNombre() + " : Elija que acciones desea hacer." + colorReset);
        }
    }

    public String turnoColor() {

        if (isTurno() == false) {
            return colorVerde;
        } else {
            return colorAzul;
        }
    }

    public void cambiarTurno() {
        setTurno(!turno);
        contadorTurno++;

    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public void tienda() {
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        int opcion;                         //Guardarla opcion del usuario

        while (!salir) {
            System.out.println("******************************************************");
            System.out.println("Tienda");
            System.out.println("******************************************************");
            System.out.println("1. Comprar");
            System.out.println("2. Vender");
            System.out.println("0. Salir");

            try {                           //verificar si el usuario escribe un numero

                System.out.println("Escribe una de las opciones");
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        comprar();
                        break;
                    case 2:
                        vender();
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                entrada.next();
            }
        }

    }

    public void comprar() {
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        int opcion;                         //Guardarla opcion del usuario

        while (!salir) {
            System.out.println("******************************************************");
            System.out.println("Comprar");
            System.out.println("******************************************************");
            System.out.println("1. Comprar Obrero // 50 galactus");
            System.out.println("2. Comprar Maestro de obra // 100 galactus");
            System.out.println("3. Comprar Arquitecto // 250 galactus");
            System.out.println("4. Comprar Ingeniero // 300 galactus");
            System.out.println("0. Salir");

            try {                           //verificar si el usuario escribe un numero

                System.out.println("Escribe una de las opciones");
                opcion = entrada.nextInt();
                entrada.nextLine();
                ImprimirTablero(planeta);
                imprimirMatriz(planeta);
                System.out.println("ingrese la coordenada del planeta para agragarle su compra");
                String coordenada = entrada.nextLine();
                int origenOutX = coordenadaX(coordenada, 0);
                int origenOutY = coordenadaY(coordenada, 0);

                System.out.println(planeta[origenOutX][origenOutY].imprimirIformacionPlaneta(planeta[origenOutX][origenOutY]));

                planeta[origenOutX][origenOutY].aniadirConstructor(planeta[origenOutX][origenOutY].crearConstructor(opcion, planeta[origenOutX][origenOutY].cantidadConstructores + 1));
                for (int i = 0; i < planeta[origenOutX][origenOutY].arregloConstructor.length; i++) {
                    if (planeta[origenOutX][origenOutY].arregloConstructor[i] != null) {
                        System.out.println(planeta[origenOutX][origenOutY].arregloConstructor[i]);
                    }
                }
                System.out.println(planeta[origenOutX][origenOutY].imprimirIformacionPlaneta(planeta[origenOutX][origenOutY]));

                salir = true;
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                entrada.next();
            }
        }

    }

    public void vender() {
        Scanner entrada = new Scanner(System.in);
 
                ImprimirTablero(planeta);
                imprimirMatriz(planeta);
                System.out.println("ingrese la coordenada del planeta de donde desea vendeer");
                String coordenada = entrada.nextLine();
                int origenOutX = coordenadaX(coordenada, 0);
                int origenOutY = coordenadaY(coordenada, 0);

                System.out.println(planeta[origenOutX][origenOutY].imprimirIformacionPlaneta(planeta[origenOutX][origenOutY]));
                for (int i = 0; i < planeta[origenOutX][origenOutY].arregloConstructor.length; i++) {
                    if (planeta[origenOutX][origenOutY].arregloConstructor[i] != null) {
                        System.out.println("#"+i+" "+planeta[origenOutX][origenOutY].arregloConstructor[i].nombre+" // "+planeta[origenOutX][origenOutY].arregloConstructor[i].venta+" galactus");
                    }
                }
                System.out.println("ingrese el numero desea vender");
                int eleccion = entrada.nextInt();
                planeta[origenOutX][origenOutY].setCantidadDinero(planeta[origenOutX][origenOutY].cantidadDinero+planeta[origenOutX][origenOutY].arregloConstructor[eleccion].venta);
                planeta[origenOutX][origenOutY].cantidadConstructores--;
                planeta[origenOutX][origenOutY].arregloConstructor[eleccion]=null;
                System.out.println(planeta[origenOutX][origenOutY].imprimirIformacionPlaneta(planeta[origenOutX][origenOutY]));
        }

    

    public void ingresarNombres() {

        Scanner entrada = new Scanner(System.in);
        String nombre;
        boolean salida = false;
        System.out.println(colorRojo + "==================||    NOTA: el nombre no debe exceder 4 letas    ||s====================" + colorReset);
        for (int i = 0; i < 2; i++) { // agraga los nombres a un arrglo
            System.out.println("Ingrese el nombre para el jugador " + (i + 1) + " : ");
            do {
                nombre = (entrada.next());

                if (nombre.length() <= 4 && nombre.length() > 0) {
                    salida = true;
                } else {
                    System.out.println(colorRojo + "INGRESO FALLIDO" + colorReset);
                    System.out.println(colorRojo + "Vuelva a ingresar el nombre" + colorReset);
                    salida = false;
                }
            } while (!salida);
            nombreJugador[i] = nombre.toUpperCase();
            System.out.println(colorVerde + "Ingresado correctamente" + colorReset);
        }
        System.out.println(colorVerde + "==================||    Nombres Ingresado    ||s====================" + colorReset);
        for (int i = 0; i < 2; i++) {

            System.out.println("Nombre jugador " + (i + 1) + ": " + nombreJugador[i]);
        }

        jugador1.setNombre(nombreJugador[0]);
        jugador2.setNombre(nombreJugador[1]);
    }

    /**
     * metodo llenar planeta, genera una cantidad de planetas indicadas por el
     * usuario, y las coloca en una posiscion aleatoria en el mapa
     *
     * @param tamanioX //este parametro es para darle un numero al random y
     * coloque en una posicion aleatoria el planeta
     * @param numeroPlanetas //el la cantidad de planetas que el usuario ingreso
     */
    public void llenarMatrizPlaneta(int tamanioX, int numeroPlanetas) {

        Random rand = new Random();
        for (int i = 0; i < numeroPlanetas - 1; i++) {
            for (int j = 0; j < numeroPlanetas; j++) {
                Random posiscion = new Random();

                planeta[posiscion.nextInt(tamanioX)][posiscion.nextInt(tamanioX)] = crearPlaneta(rand.nextInt(4), i);
            }
            numeroPlanetas--;

        }
    }

    /**
     * imprimirMatriz es un metodo que sirve para imprimir una matriz enviada
     * como parametro, muestra en donde estan ubicados los planetas en el mapa
     *
     * @param matriz es el parametro en el que se guarda la matriz enviada a la
     * hora de llamar al metodo
     */
    public void imprimirMatriz(Planeta[][] matriz) {

        for (int x = 0; x < matriz.length; x++) {

            for (int y = 0; y < matriz[x].length; y++) {

                if (matriz[x][y] != null) {

                    switch (matriz[x][y].propietario) {
                        case 0:
                            System.out.print(coloreAmarillo + String.format("|%4s", matriz[x][y].getNombre() + " // Neutral " + " // " + letras[x + 1] + "" + (y + 1) + "//(" + x + "," + y + "|", "|%") + colorReset);
                            break;
                        case 1:
                            System.out.print(colorVerde + String.format("|%4s", matriz[x][y].getNombre() + " // " + jugador1.getNombre() + " // " + letras[x + 1] + "" + (y + 1) + "//(" + x + "," + y + "|", "|%") + colorReset);
                            break;
                        case 2:
                            System.out.print(colorAzul + String.format("|%4s", matriz[x][y].getNombre() + " // " + jugador2.getNombre() + " // " + letras[x + 1] + "" + (y + 1) + "//(" + x + "," + y + "|", "|%") + colorReset);
                            break;
                    }
                }
//                } else {
//                    System.out.print(""
//                            + "|Vacio|");
//                }
//
//                if (y != matriz[x].length - 1) {
//                    System.out.print("\t\t");
//                }

            }
            System.out.println(" \n");
        }
    }

    public static String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * crearPlaneta, ayuda a crear los planetas indicados por el metodo
     * llenarMatriz
     *
     * @param tipo es el tipo de planeta que se va a generar, este parametro
     * ayuda a calcular la posibilidad que se genere ese planeta
     * @param pos es el contador de planetas generados y ayuda a colocarle
     * nombre a los planetas generados con letras obtenidas del arreglo abc
     * @return
     */
    private Planeta crearPlaneta(int tipo, int pos) {

        int random = (int) (Math.random() * 100 + 1);
        if (random > 95) {
            tipo = 4;
        } else if (random > 85 && random <= 95) {
            tipo = 3;
        } else if (random > 70 && random <= 85) {
            tipo = 2;
        } else if (random > 45 && random <= 70) {
            tipo = 1;
        } else if (random <= 45) {
            tipo = 0;
        }

        Planeta actual = null;
        switch (tipo) {
            case 0:
                actual = new Tierra("Tierra-" + abc[pos], 0, 0, 0, 0);
                actual.letalidad();
                actual.generearGerrero(1, 15, 25);
                actual.generearConstructor();
                actual.generearNave(1, 3);
                actual.generarDinero(100, 500);

                break;
            case 1:
                actual = new Agua("Agua-" + abc[pos], 0, 0, 0, 0);
                actual.letalidad();
                actual.generearGerrero(2, 12, 33);
                actual.generearConstructor();
                actual.generearNave(1, 3);
                actual.generarDinero(100, 500);

                break;
            case 2:
                actual = new Fuego("Fuego-" + abc[pos], 0, 0, 0, 0);
                actual.letalidad();
                actual.generearGerrero(3, 10, 20);
                actual.generearConstructor();
                actual.generearNave(1, 3);
                actual.generarDinero(100, 500);

                break;
            case 3:
                actual = new Organico("Organico-" + abc[pos], 0, 0, 0, 0);
                actual.letalidad();
                actual.generearGerrero(4, 5, 15);
                actual.generearConstructor();
                actual.generearNave(1, 3);
                actual.generarDinero(100, 500);

                break;
            case 4:
                actual = new Radioactivo("Radioactivo-" + abc[pos], 0, 0, 0, 0);
                actual.letalidad();
                actual.generearGerrero(5, 3, 25);
                actual.generearConstructor();
                actual.generearNave(1, 3);
                actual.generarDinero(100, 500);

                break;
            default:
                break;
        }
        return actual;

    }

    /**
     * ImprimirTablero, ayuda a imprimir una referecia del mapa para que el
     * usuario vea en donde estan los planetas y se guie con coordenadas
     *
     * @param tamanioX es el tamanio del mapa en filas, indicado por el usuario
     * @param tamanioY es el tamanio del mapa en columnas, indicado por el
     * usuario
     */
    public void ImprimirTablero(Planeta[][] matriz) {
        String color = colorBlanco;
        tablero = new String[tamanioX + 1][tamanioY + 1];
        for (int i = 1; i < matriz.length; i++) {
            for (int j = 1; j < matriz[i].length; j++) {

                if (planeta[i - 1][j - 1] != null) {
                    if (planeta[i - 1][j - 1].propietario == 1) {
                        tablero[j][i] = colorVerde + "█" + colorReset;
                    } else if (planeta[i - 1][j - 1].propietario == 2) {
                        tablero[j][i] = colorAzul + "█" + colorReset;
                    } else {
                        tablero[j][i] = coloreAmarillo + "█" + colorReset;
                    }

                } else {
                    tablero[j][i] = colorReset + "░" + colorReset;
                }

            }
        }

        for (int i = 1; i < matriz.length; i++) {
            tablero[i][0] = String.valueOf(i); //Aquí se añaden los números de la
        }                                           //izquierda aprovechando el indice del for

        for (int j = 1; j < matriz.length; j++) //Aquí rellenamos con las vocales del array
        {
            tablero[0][j] = abc[j - 1];
        }

//para eliminar el null que queda en la posicion [0][0] tan fácil como:
        tablero[0][0] = "";
        System.out.print(" \t"); //dejar un espacio en blanco

        System.out.println();
        //ahora imprimimos todas las filas y columnas del tablero
        //este bucle for controla la impresión de las líneas
        for (int i = 0; i < matriz.length; i++) {
            //lo primero que se imprime en la línea es el número

            //ahora continuamos con la impresión del tablero
            for (int j = 0; j < matriz[i].length; j++) {

                System.out.print(color + tablero[i][j] + "\t" + colorReset);

            }

            System.out.println();
            System.out.println();

        }
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    public Planeta[][] getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta[][] planeta) {
        this.planeta = planeta;
    }

    public Constructor[] getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor[] constructor) {
        this.constructor = constructor;
    }

}
